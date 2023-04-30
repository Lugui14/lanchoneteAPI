create table if not exists "user"(

    iduser bigserial not null,
    login varchar(100) not null,
    password varchar(255) not null,

    primary key(iduser)

);