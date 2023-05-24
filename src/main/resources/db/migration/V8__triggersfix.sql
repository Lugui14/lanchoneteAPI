DROP TRIGGER IF EXISTS updatecontrolval ON public.request;
create trigger updatecontrolval before
    update
    on
        public.request for each row execute procedure addreqvaltocontrol();

DROP TRIGGER IF EXISTS cancelcontrolval ON public.request;