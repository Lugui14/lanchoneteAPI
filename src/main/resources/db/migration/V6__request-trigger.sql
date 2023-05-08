DROP TRIGGER updatecontrolval ON public.request;

create trigger updatecontrolval after
    insert or update
    on
        public.request for each row execute procedure addreqvaltocontrol();