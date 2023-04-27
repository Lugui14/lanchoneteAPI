create sequence "public.category_idcategory_seq";

alter sequence "public.category_idcategory_seq" owner to postgres;

create sequence "public.product_idproduct_seq";

alter sequence "public.product_idproduct_seq" owner to postgres;

create sequence "public.control_idcontrol_seq";

alter sequence "public.control_idcontrol_seq" owner to postgres;

create sequence "public.request_idrequest_seq";

alter sequence "public.request_idrequest_seq" owner to postgres;

create sequence "public.waiter_idwaiter_seq";

alter sequence "public.waiter_idwaiter_seq" owner to postgres;

create table if not exists category
(
    idcategory       bigint  default nextval('"public.category_idcategory_seq"'::regclass) not null
    constraint category_pk
    primary key,
    category         varchar(255)                                                          not null
    constraint "public.category_nmcategory_key"
    unique,
    description      text,
    iscategoryactive boolean default true                                                  not null
    );

alter table category
    owner to postgres;

alter sequence "public.category_idcategory_seq" owned by category.idcategory;

create table if not exists product
(
    idproduct       bigint  default nextval('"public.product_idproduct_seq"'::regclass) not null
    constraint product_pk
    primary key,
    product         varchar(255)                                                        not null
    constraint "public.product_nmproduct_key"
    unique,
    price           double precision                                                    not null,
    description     text,
    idcategory      bigint                                                              not null
    constraint product_fk_category
    references category,
    isproductactive boolean default true                                                not null
    );

alter table product
    owner to postgres;

alter sequence "public.product_idproduct_seq" owned by product.idproduct;

create table if not exists waiter
(
    idwaiter bigint default nextval('"public.waiter_idwaiter_seq"'::regclass) not null
    constraint waiter_pk
    primary key,
    waiter   varchar(255)                                                     not null
    constraint "public.waiter_nmwaiter_key"
    unique,
    salary   double precision                                                 not null
    );

alter table waiter
    owner to postgres;

alter sequence "public.waiter_idwaiter_seq" owned by waiter.idwaiter;

create table if not exists control
(
    idcontrol bigint           default nextval('"public.control_idcontrol_seq"'::regclass) not null
    constraint control_pk
    primary key,
    client    varchar(255)
    constraint "public.control_nmclient_key"
    unique,
    topay     double precision,
    isclosed  boolean          default false                                               not null,
    idwaiter  bigint                                                                       not null
    constraint comanda_fk_waiter
    references waiter,
    entrace   timestamp,
    discount  double precision default 0                                                   not null
    );

alter table control
    owner to postgres;

alter sequence "public.control_idcontrol_seq" owned by control.idcontrol;

create table if not exists request
(
    idrequest     bigint           default nextval('"public.request_idrequest_seq"'::regclass) not null
    constraint requests_pk
    primary key,
    idproduct     bigint                                                                       not null
    constraint request_fk_product
    references product,
    idcontrol     bigint                                                                       not null
    constraint request_fk_control
    references control,
    requeststatus integer          default 0                                                   not null,
    vlvenda       double precision default 0                                                   not null
    );

comment on column request.requeststatus is '0 - Pedido
1 - Preparando
2 - Pronto
3 - Cancelado';

alter table request
    owner to postgres;

alter sequence "public.request_idrequest_seq" owned by request.idrequest;

create or replace function addreqvaltocontrol() returns trigger
    language plpgsql
as
$$
	declare
newvalue float8;

begin

select topay + new.vlvenda into newvalue from "control" where idcontrol = new.idcontrol;

if old.requeststatus <> 1
		and old.requeststatus <> 2
		and new.requeststatus <> old.requeststatus
		and new.requeststatus <> 3
		and new.requeststatus <> 0 then
update "control"
set topay = newvalue
where idcontrol = new.idcontrol;
end if;

return new;
end;
$$;

alter function addreqvaltocontrol() owner to postgres;

create trigger updatecontrolval
    after update
    on request
    for each row
    execute procedure addreqvaltocontrol();

create or replace function removereqvaltocontrol() returns trigger
    language plpgsql
as
$$
begin

		if new.requeststatus = 0
		and old.requeststatus <> 3
		and old.requeststatus <> new.requeststatus
		or new.requeststatus = 3
		and old.requeststatus <> 0
		and old.requeststatus <> new.requeststatus
		then
update "control"
set topay = topay - new.vlvenda
where idcontrol = new.idcontrol;
end if;

return new;
end;
$$;

alter function removereqvaltocontrol() owner to postgres;

create trigger cancelcontrolval
    after update
    on request
    for each row
    execute procedure removereqvaltocontrol();

create or replace function addproductvaltorequest() returns trigger
    language plpgsql
as
$$

	declare
productprice decimal;

begin

select price into productprice from product where idproduct = new.idproduct;

update request set vlvenda = productprice where idrequest = new.idrequest;

return new;
end

$$;

alter function addproductvaltorequest() owner to postgres;

create trigger addvlvenda
    after insert
    on request
    for each row
    execute procedure addproductvaltorequest();

create or replace function watchcontroldiscount() returns trigger
    language plpgsql
as
$$
begin

		if new.topay < 10
		or new.discount > 0.5
		then
			return null;
end if;

return new;
end
$$;

alter function watchcontroldiscount() owner to postgres;

create trigger watchcontrol
    before insert or update
                         on control
                         for each row
                         execute procedure watchcontroldiscount();
