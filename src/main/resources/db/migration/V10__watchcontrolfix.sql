CREATE OR REPLACE FUNCTION public.watchcontroldiscount()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
	declare
newprice float8 := new.topay;
		newdiscount float8 := new.discount - old.discount;
begin

		if newprice < 0
		or new.discount > 0.5
		or new.discount < 0
		then
			return null;
end if;

		if newprice < 10 then
			new.discount = 0;
return new;
end if;

		if new.discount <> old.discount then
			new.topay = old.topay - newdiscount;
end if;
return new;
end
$function$
;

CREATE OR REPLACE FUNCTION public.addreqvaltocontrol()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$

begin

		if new.requeststatus = 1
		or new.requeststatus = 2
		then
			if old.requeststatus = 1
			or old.requeststatus = 2
			or old.requeststatus = new.requeststatus
			then
				return new;
end if;

update "control"
set topay = topay + new.vlvenda
where idcontrol = new.idcontrol;
return new;
end if;

		if new.requeststatus = 0
		or new.requeststatus = 3
		or new.requeststatus = 4
		then
			if old.requeststatus = 0
			or old.requeststatus = 3
			or old.requeststatus = 4
			then
				return new;
end if;

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
