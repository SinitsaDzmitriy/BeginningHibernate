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

create table PersonAddress
(
    person_id bigint not null,
    address_id bigint not null,
    primary key (person_id, address_id)
);

alter table Address
add constraint UK_hqi9ytv7objs9otwcub9btpu2 unique (postalCode);

alter table Person
add constraint UK_23enodonj49jm8uwec4i7y37f unique (passportNumber);

alter table PersonAddress
add constraint FK8b3lru5fyej1aarjflamwghqq foreign key (person_id)
references Person;

alter table PersonAddress
add constraint FK7p69mgialumhegyl4byrh65jk foreign key (address_id)
references Address;

select personaddr_.person_id, personaddr_.address_id
from PersonAddress personaddr_
where personaddr_.person_id=1 and personaddr_.address_id=2;

select personaddr_.person_id, personaddr_.address_id
from PersonAddress personaddr_
where personaddr_.person_id=1 and personaddr_.address_id=3;

insert into Person
(name, passportNumber, id)
values
('Homeless Name', 'HN0000000', 1);

insert into Address
(place, postalCode, rating, id)
values
('dump', 'none', 0, 2);

insert into Address
(place, postalCode, rating, id)
values
('hospital', '0000001', 10, 3);

insert into PersonAddress
(person_id, address_id)
values
(1, 2);

insert into PersonAddress
(person_id, address_id)
values
(1, 3);

select
    person0_.id as id1_1_0_,
    person0_.name as name2_1_0_,
    person0_.passportNumber as passport3_1_0_
from Person person0_
where person0_.id=1;

select
    address0_.id as id1_0_0_,
    address0_.place as place2_0_0_,
    address0_.postalCode as postalCo3_0_0_,
    address0_.rating as rating4_0_0_
from Address address0_
where address0_.id=3;

select
    owners0_.address_id as address_2_2_0_,
    owners0_.person_id as person_i1_2_0_,
    owners0_.person_id as person_i1_2_1_,
    owners0_.address_id as address_2_2_1_,
    person1_.id as id1_1_2_,
    person1_.name as name2_1_2_,
    person1_.passportNumber as passport3_1_2_
from PersonAddress owners0_
inner join Person person1_ on owners0_.person_id=person1_.id
where owners0_.address_id=3;

/*
    2019-07-18 15:12:15,944 TRACE [descriptor.sql.BasicBinder, bind] binding parameter [1] as [BIGINT] - [3]
    2019-07-18 15:12:15,946 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_1_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,946 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([address_2_2_1_] : [BIGINT]) - [3]
    2019-07-18 15:12:15,946 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([id1_1_2_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,947 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([address_2_2_0_] : [BIGINT]) - [3]
    2019-07-18 15:12:15,947 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_0_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,947 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([address_2_2_0_] : [BIGINT]) - [3]
    Hibernate:
select
    addresses0_.person_id as person_i1_2_0_,
    addresses0_.address_id as address_2_2_0_,
    addresses0_.person_id as person_i1_2_1_,
    addresses0_.address_id as address_2_2_1_,
    address1_.id as id1_0_2_,
    address1_.place as place2_0_2_,
    address1_.postalCode as postalCo3_0_2_,
    address1_.rating as rating4_0_2_
from
    PersonAddress addresses0_
        inner join
    Address address1_
    on addresses0_.address_id=address1_.id
where
        addresses0_.person_id=?
                                  2019-07-18 15:12:15,949 TRACE [descriptor.sql.BasicBinder, bind] binding parameter [1] as [BIGINT] - [1]
    2019-07-18 15:12:15,949 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_1_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,949 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([address_2_2_1_] : [BIGINT]) - [2]
    2019-07-18 15:12:15,949 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([id1_0_2_] : [BIGINT]) - [2]
    2019-07-18 15:12:15,949 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([place2_0_2_] : [VARCHAR]) - [dump]
    2019-07-18 15:12:15,950 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([postalCo3_0_2_] : [VARCHAR]) - [none]
    2019-07-18 15:12:15,950 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([rating4_0_2_] : [INTEGER]) - [0]
    2019-07-18 15:12:15,950 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_0_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,950 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_0_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,950 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([address_2_2_0_] : [BIGINT]) - [2]
    2019-07-18 15:12:15,951 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_1_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,951 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([address_2_2_1_] : [BIGINT]) - [3]
    2019-07-18 15:12:15,951 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([id1_0_2_] : [BIGINT]) - [3]
    2019-07-18 15:12:15,951 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_0_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,951 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_0_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,951 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([address_2_2_0_] : [BIGINT]) - [3]
    2019-07-18 15:12:15,952 TRACE [CollectionType, getCollection] Created collection wrapper: [edu.hibernate.samples.association.many2many.link.entities.Address.owners#2]
    Hibernate:
delete
from
    PersonAddress
where
        person_id=?
  and address_id=?
                     2019-07-18 15:12:15,957 TRACE [descriptor.sql.BasicBinder, bind] binding parameter [1] as [BIGINT] - [1]
    2019-07-18 15:12:15,958 TRACE [descriptor.sql.BasicBinder, bind] binding parameter [2] as [BIGINT] - [3]
    Hibernate:
select
    person0_.id as id1_1_0_,
    person0_.name as name2_1_0_,
    person0_.passportNumber as passport3_1_0_
from
    Person person0_
where
        person0_.id=?
                        2019-07-18 15:12:15,960 TRACE [descriptor.sql.BasicBinder, bind] binding parameter [1] as [BIGINT] - [1]
    2019-07-18 15:12:15,960 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([name2_1_0_] : [VARCHAR]) - [Homeless Name]
    2019-07-18 15:12:15,960 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([passport3_1_0_] : [VARCHAR]) - [HN0000000]
    2019-07-18 15:12:15,961 TRACE [CollectionType, getCollection] Created collection wrapper: [edu.hibernate.samples.association.many2many.link.entities.Person.addresses#1]
    Hibernate:
select
    addresses0_.person_id as person_i1_2_0_,
    addresses0_.address_id as address_2_2_0_,
    addresses0_.person_id as person_i1_2_1_,
    addresses0_.address_id as address_2_2_1_,
    address1_.id as id1_0_2_,
    address1_.place as place2_0_2_,
    address1_.postalCode as postalCo3_0_2_,
    address1_.rating as rating4_0_2_
from
    PersonAddress addresses0_
        inner join
    Address address1_
    on addresses0_.address_id=address1_.id
where
        addresses0_.person_id=?
                                  2019-07-18 15:12:15,961 TRACE [descriptor.sql.BasicBinder, bind] binding parameter [1] as [BIGINT] - [1]
    2019-07-18 15:12:15,961 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_1_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,961 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([address_2_2_1_] : [BIGINT]) - [2]
    2019-07-18 15:12:15,962 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([id1_0_2_] : [BIGINT]) - [2]
    2019-07-18 15:12:15,962 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([place2_0_2_] : [VARCHAR]) - [dump]
    2019-07-18 15:12:15,962 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([postalCo3_0_2_] : [VARCHAR]) - [none]
    2019-07-18 15:12:15,962 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([rating4_0_2_] : [INTEGER]) - [0]
    2019-07-18 15:12:15,962 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_0_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,962 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([person_i1_2_0_] : [BIGINT]) - [1]
    2019-07-18 15:12:15,963 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([address_2_2_0_] : [BIGINT]) - [2]
    2019-07-18 15:12:15,963 TRACE [CollectionType, getCollection] Created collection wrapper: [edu.hibernate.samples.association.many2many.link.entities.Address.owners#2]
    Hibernate:
delete
from
    PersonAddress
where
        person_id=?
  and address_id=?
                     2019-07-18 15:12:15,964 TRACE [descriptor.sql.BasicBinder, bind] binding parameter [1] as [BIGINT] - [1]
    2019-07-18 15:12:15,964 TRACE [descriptor.sql.BasicBinder, bind] binding parameter [2] as [BIGINT] - [2]
    Hibernate:
delete
from
    Person
where
        id=?
               2019-07-18 15:12:15,965 TRACE [descriptor.sql.BasicBinder, bind] binding parameter [1] as [BIGINT] - [1]
*/