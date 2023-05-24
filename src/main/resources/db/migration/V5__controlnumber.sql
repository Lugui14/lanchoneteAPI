alter table "control"
 add column if not exists controlnumber integer not null default 0;

update "control"
   set controlnumber = idcontrol;