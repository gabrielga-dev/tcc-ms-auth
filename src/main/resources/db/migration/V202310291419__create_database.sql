create table person
(
    uuid          varchar(36) primary key not null,
    first_name    varchar(75)             not null,
    last_name     varchar(150)            not null,
    cpf           varchar(14)             not null,
    email         varchar(100)            not null,
    password      varchar(255)            not null,
    creation_date date                    not null,
    active        boolean                 not null default false,
    update_date   date
);

create table email_validation
(
    uuid            varchar(36) primary key not null,
    person_uuid     varchar(36)             not null,
    type            varchar(30)             not null,
    validation_date date,
    validated       boolean                 not null default false,
    creation_date   date                    not null,
    FOREIGN KEY (person_uuid) REFERENCES person (uuid)
);
create table role
(
    id          bigint primary key not null auto_increment,
    name        varchar(100),
    description varchar(500)
);

create table person_role
(
    person_uuid varchar(36) not null,
    role_id bigint not null,
    FOREIGN KEY (person_uuid) REFERENCES person (uuid),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

create table service
(
    person_uuid varchar(36) not null,
    service_uuid varchar(36) not null,
    type        varchar(100),
    primary key (person_uuid, service_uuid),
    FOREIGN KEY (person_uuid) REFERENCES person (uuid)
);

insert into role(name, description) values ('CONTRACTOR', 'Who will contract services and create events.');
insert into role(name, description) values ('MUSICIAN', 'Who will be inserted on the platform to play musics.');
insert into role(name, description) values ('BAND', 'Who will create bands to play on events.');
