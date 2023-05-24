CREATE OR REPLACE FUNCTION public.watchcontroldiscount()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
	declare
newprice float8 := new.topay;
		newdiscount float8 := new.discount - old.discount;
begin

		if newprice < 0 or new.discount > 0.5 or new.discount < 0 then
			return null;
end if;

		if newprice < 10 then
			new.discount = 0;
return new;
end if;

		new.topay = old.topay - newdiscount;
return new;
end
$function$
;

DROP TRIGGER IF EXISTS watchcontrol ON public."control";
create trigger watchcontrol before
    update
    on
        public.control for each row execute procedure watchcontroldiscount();
