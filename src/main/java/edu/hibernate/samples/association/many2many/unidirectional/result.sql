create table Address
(
    id bigint not null,
    place varchar(255),
    rating integer,
    primary key (id)
);

create table Person
(
    id bigint not null,
    name varchar(255),
    primary key (id)
);

create table Person_Address
(
    Person_id bigint not null,
    apartments_id bigint not null
);

alter table Person_Address
add constraint FKkkxfgb1nk4rm209drula4hk0o foreign key (apartments_id)
references Address;


alter table Person_Address
add constraint FKba7rc9qe2vh44u93u0p2auwti foreign key (Person_id)
references Person;

insert into Person
(name, id)
values
('Son Name', 1);

insert into Address
(place, rating, id)
values
('family house', 5, 2);

insert into Address
(place, rating, id)
values
('hostel', 3, 3);

insert into Person
(name, id)
values
('Dad Name', 4);

insert into Person_Address
(Person_id, apartments_id)
values
(1, 2);

insert into Person_Address
(Person_id, apartments_id)
values
(1, 3);

insert into Person_Address
(Person_id, apartments_id)
values
(4, 2);

-- Entity removal from the @ManyToMany collection.

/*
    Hibernate simply deletes all entries associated with a given parent.
    In this case, all linking records referring to the Person entity with id = 1.
*/
delete from Person_Address
where Person_id=1;

--  Hibernates recreated rows listed in the current running persistent context.
insert into Person_Address
(Person_id, apartments_id)
values
(1, 2);