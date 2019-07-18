create table Address
(
    id bigint not null,
    place varchar(255),
    postalCode varchar(255),
    rating integer,
    primary key (id)
);

create table Person
(
    id bigint not null,
    name varchar(255),
    passportNumber varchar(255),
    primary key (id)
);

create table Person_Address
(
    owners_id bigint not null,
    apartments_id bigint not null
);

alter table Address
add constraint UK_hqi9ytv7objs9otwcub9btpu2 unique (postalCode);

alter table Person
add constraint UK_23enodonj49jm8uwec4i7y37f unique (passportNumber);

alter table Person_Address
add constraint FKkkxfgb1nk4rm209drula4hk0o foreign key (apartments_id)
references Address;

alter table Person_Address
add constraint FKbn86l24gmxdv2vmekayqcsgup foreign key (owners_id)
references Person;

insert into Person
(name, passportNumber, id)
values
('Son Name', 'SN0000000', 1);

insert into Address
(place, postalCode, rating, id)
values
('family house', '000002', 5, 2);

insert into Address
(place, postalCode, rating, id)
values
('hostel', '000001', 3, 5);

insert into Person
(name, passportNumber, id)
values
('Dad Name', 'DN0000000', 4);

insert into Person_Address
(owners_id, apartments_id)
values
(1, 2);

insert into Person_Address
(owners_id, apartments_id)
values
(1, 3);

insert into Person_Address
(owners_id, apartments_id)
values
(4, 2);

delete from Person_Address
where owners_id=1;

insert into Person_Address
(owners_id, apartments_id)
values
(1, 3);