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