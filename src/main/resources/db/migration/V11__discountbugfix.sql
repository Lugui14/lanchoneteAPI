drop trigger if exists cancelcontrolval on public.request;
drop function if exists public.removereqvaltocontrol;

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

		if new.discount <> old.discount then
			new.topay = old.topay - newdiscount;
end if;
return new;
end
$function$
;

ALTER TABLE public."control" DROP CONSTRAINT if exists "public.control_nmclient_key";
