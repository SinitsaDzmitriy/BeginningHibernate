create table Person
(
    id bigint not null,
    name varchar(255),
    primary key (id)
);

create table Phone
(
    id bigint not null,
    number varchar(255),
    person_id bigint,
    primary key (id)
);

alter table Phone
add constraint PERSON_ID_FK foreign key (person_id) references Person;

insert into Person
(name, id)
values
('Sinitsa Dzmitry', 1);

insert into Phone
(number, person_id, id)
values
('+375298029953', 1, 2);

update Phone
set number='+375298029953', person_id=NULL
where id=2;