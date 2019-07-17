create table Person
(
id bigint not null,
name varchar(255),
primary key (id)
);

create table Person_Phone
(
    Person_id bigint not null,
    phones_id bigint not null
);

create table Phone
(
    id bigint not null,
    number varchar(255),
    primary key (id)
);

alter table Person_Phone
add constraint UK_9uhc5itwc9h5gcng944pcaslf unique (phones_id) references Phone;

alter table Person_Phone
add constraint FK2ex4e4p7w1cj310kg2woisjl2 foreign key (Person_id) references Person;

insert into Person
(name, id)
values
('Sinitsa Dzmitry', 1);

insert into Phone
(number, id)
values
('+375(29)802-99-53', 2);

insert into Phone
(number, id)
values
(50-28-43, 3);

insert into Person_Phone
(Person_id, phones_id)
values
(1, 2);

insert into Person_Phone
(Person_id, phones_id)
values
(1, 3);

delete from Person_Phone
where Person_id = 1;

insert into Person_Phone
(Person_id, phones_id)
values
(1, 3);

delete from Phone
where id=2;