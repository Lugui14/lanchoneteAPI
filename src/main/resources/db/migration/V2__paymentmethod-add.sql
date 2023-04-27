create TABLE if not exists "paymentmethod" (
	"idpaymentmethod" bigserial NOT NULL,
	"paymentmethod" varchar(30) NOT null unique,
	"valmoved" float8 not null default 0,
	CONSTRAINT "paymentmethod_pk" PRIMARY KEY ("idpaymentmethod")
) WITH (
  OIDS=FALSE
);  

create TABLE if not exists "payment" (
	"idpayment" bigserial NOT NULL,
	"idcontrol" bigint not null,
	"idpaymentmethod" bigint NOT NULL,
	"amountpaid" FLOAT NOT NULL,
	"paymentdetail" TEXT,
	CONSTRAINT "payment_pk" PRIMARY KEY ("idpayment"),
	constraint "payment_fk_control" foreign key (idcontrol) references "control"(idcontrol),
	constraint "payment_fk_paymentmethod" foreign key (idpaymentmethod) references paymentmethod(idpaymentmethod)
	) WITH (
  OIDS=FALSE
);

create or replace function addpaymenttotal() returns trigger as $$
	begin 
			update paymentmethod
			   set valmoved = valmoved + new.amountpaid
			 where idpaymentmethod = new.idpaymentmethod;
		 	
		return new;
	end 
$$ language plpgsql;

create or replace function updatepaymenttotal() returns trigger as $$
	begin 
			update paymentmethod
			   set valmoved = valmoved + new.amountpaid - old.amountpaid
			 where idpaymentmethod = new.idpaymentmethod;
		 	
		return new;
	end 
$$ language plpgsql;

create or replace function removepaymenttotal() returns trigger as $$
	begin 
		
		update paymentmethod
		   set valmoved = valmoved - old.amountpaid
		 where idpaymentmethod = old.idpaymentmethod;
		 	
		return new;
	end 
$$ language plpgsql;

create or replace function addpaymenttocontrol() returns trigger as $$
	begin 
	   
	   update "control"
		  set topay = topay - new.amountpaid
		where idcontrol = new.idcontrol;
	
   	   update "control"
		  set topay = 0
		where topay < 0
		   or topay = null;
		 	
		return new;
	end 
$$ language plpgsql;

create or replace function updatepaymenttocontrol() returns trigger as $$
	begin 
		
	   update "control"
		  set topay = topay - new.amountpaid + old.amountpaid
		where idcontrol = new.idcontrol;
	
	   update "control"
		  set topay = 0
		where topay < 0
		   or topay = null;
		
		return new;
	end 
$$ language plpgsql;

create or replace function removepaymenttocontrol() returns trigger as $$
	begin 
		
	   update "control"
		  set topay = topay + old.amountpaid
		where idcontrol = new.idcontrol;
		 	
		return new;
	end 
$$ language plpgsql;

alter table "control"
alter column topay set default 0;

 alter table "control"
alter column topay set not null;

 alter table "control"
alter column entrace set default now();

CREATE OR REPLACE FUNCTION public.removereqvaltocontrol()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
	begin
		
		if new.requeststatus = 0
		and old.requeststatus <> 4
		and old.requeststatus <> new.requeststatus
		or new.requeststatus = 4
		and old.requeststatus <> 0
		and old.requeststatus <> new.requeststatus
		then
			update "control"
				 set topay = topay - new.vlvenda
			   where idcontrol = new.idcontrol;
		end if;
		
		update "control"
		   set topay = 0
		 where topay < 0;
	
		return new;
	end;
$function$
;

CREATE OR REPLACE FUNCTION public.watchcontroldiscount()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
	begin
		
		if (new.discount = old.discount or 
		new.discount = 0) and new.topay > 0 then
			return new;
		end if;
		
		if (new.topay < 10 and new.discount > 0) or
		(new.topay > 10 and new.discount > 0.5) or 
		(new.topay < 0)
		then	
			return null;
		end if;
	
		if new.topay = 0 then
			update request
			   set requeststatus = 3
			 where idcontrol = new.idcontrol;
		end if;
	
		return new;
	end
$function$
;