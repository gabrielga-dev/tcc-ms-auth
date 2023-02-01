create table verification
(
    uuid          varchar(36) primary key not null,
    person_uuid   varchar(36)             not null,
    type          varchar(30)             not null,
    creation_date date                    not null,
    FOREIGN KEY (person_uuid) REFERENCES person (uuid)
);