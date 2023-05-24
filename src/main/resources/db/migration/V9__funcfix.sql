CREATE OR REPLACE FUNCTION public.addreqvaltocontrol()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
	declare
newvalue float8;

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
select topay + new.vlvenda into newvalue from "control" where idcontrol = new.idcontrol;

update "control"
set topay = newvalue
where idcontrol = new.idcontrol;
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
select topay - new.vlvenda into newvalue from "control" where idcontrol = new.idcontrol;

update "control"
set topay = newvalue
where idcontrol = new.idcontrol;
end if;


update "control"
set topay = 0
where topay < 0;


return new;
end;
$function$
;

