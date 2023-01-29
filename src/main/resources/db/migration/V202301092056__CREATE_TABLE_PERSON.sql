create table person
(
    uuid          varchar(36) primary key not null,
    first_name    varchar(75)             not null,
    last_name     varchar(150)            not null,
    cpf           varchar(14)             not null,
    email         varchar(100)            not null,
    password      varchar(255)            not null,
    creation_date date                    not null,
    update_date   date
);