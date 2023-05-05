alter table "control"
 add column controlnumber integer not null default 0;

update "control"
   set controlnumber = idcontrol;