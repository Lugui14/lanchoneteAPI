drop table "user";

create table if not exists "useradmin"(

    iduser bigserial not null,
    login varchar(100) not null,
    password varchar(255) not null,

    primary key(iduser)

);