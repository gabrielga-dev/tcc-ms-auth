create table service
(
    person_uuid varchar(36) not null,
    service_uuid varchar(36) not null,
    type        varchar(100),
    primary key (person_uuid, service_uuid),
    FOREIGN KEY (person_uuid) REFERENCES person (uuid)
);