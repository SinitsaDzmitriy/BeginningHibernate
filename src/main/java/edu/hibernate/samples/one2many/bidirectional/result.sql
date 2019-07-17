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

-- Makes the field number unique
alter table Phone
add constraint UK_l329ab0g4c1t78onljnxmbnp6 unique (number);

-- Marks the person_id in the Person table as a foreign key
alter table Phone
add constraint FKmw13yfsjypiiq0i1osdkaeqpg foreign key (person_id) references Person;

-- Insert of the Person entity
insert into Person
(name, id)
values
('Sinitsa Dzmitry', 1);

-- Cascaded inserts of the Phone entities related to the persisted Person entity
insert into Phone
(number, person_id, id)
values
('+375(29)802-99-53', 1, 2);

insert into Phone
(number, person_id, id)
values
('50-28-43', 1, 3);

insert into Phone
(number, person_id, id)
values
('+375(17)309-17-09', 1, 4);

/*
    Removing a Phone entity object from the Person's collection leads to deletion of the associated entity
from the Phone database table.
*/
delete from Phone
where id=2;

// Cascaded removal of the Person entity
delete
from Phone
where id=3;

delete
from Phone
where id=4;

delete
from Person
where id=1;