create table card_mappings
(
    id bigint not null,
    frequency double,
    dest_card_id bigint,
    source_card_id bigint,
    primary key (id)
);

create table cards
(
    id bigint not null,
    content varchar(255),
    definition varchar(255),
    transcription varchar(255),
    type_id bigint,
    primary key (id)
);

create table langs
(
    id bigint not null,
    code varchar(255),
    english_name varchar(255),
    local_name varchar(255),
    primary key (id)
);

create table Type
(
    id bigint not null,
    name varchar(255),
    lang_id bigint,
    primary key (id)
);

alter table card_mappings
add constraint SOURCE_DEST_UQ unique (source_card_id, dest_card_id);

alter table card_mappings
add constraint DEST_CARD_ID_FK
foreign key (dest_card_id) references cards;

alter table card_mappings
add constraint SOURCE_CARD_ID_FK
foreign key (source_card_id) references cards;

alter table cards
add constraint TYPE_ID_FK
foreign key (type_id) references Type;

alter table Type
add constraint LANG_ID_FK
foreign key (lang_id) references langs;

insert into langs
(code, english_name, local_name, id)
values
('rus', 'Russian', 'Русский', 3);

insert into Type
(lang_id, name, id)
values
(3, 'существительное', 2);

insert into cards
(content, definition, transcription, type_id, id)
values
(
    'машина',
    'моторное дорожное транспортное средство, используемое для перевозки людей или грузов.',
    null,
    2,
    1
);

insert into langs
(code, english_name, local_name, id)
values
('eng', 'English', 'English', 6);

insert into Type
(lang_id, name, id)
values
(6, 'noun', 5);

insert into cards
(content, definition, transcription, type_id, id)
values
(
    'car',
    'a road vehicle, typically with four wheels, powered by an internal combustion engine and able to carry a small number of people.',
    null,
    5,
    4
);


insert into cards
(content, definition, transcription, type_id, id)
values
(
    'auto',
    'a car.',
    null,
    5,
    7
);

insert into card_mappings
(dest_card_id, frequency, source_card_id, id)
values
(4, 1.0, 1, 8);

insert into card_mappings
(dest_card_id, frequency, source_card_id, id)
values
(7, 0.8, 1, 9);

select
    card0_.id as id1_1_0_,
    card0_.content as content2_1_0_,
    card0_.definition as definiti3_1_0_,
    card0_.transcription as transcri4_1_0_,
    card0_.type_id as type_id5_1_0_
from cards card0_
where card0_.id=1;

select
    cardmappin0_.source_card_id as source_c4_0_0_,
    cardmappin0_.id as id1_0_0_,
    cardmappin0_.id as id1_0_1_,
    cardmappin0_.dest_card_id as dest_car3_0_1_,
    cardmappin0_.frequency as frequenc2_0_1_,
    cardmappin0_.source_card_id as source_c4_0_1_,
    card1_.id as id1_1_2_,
    card1_.content as content2_1_2_,
    card1_.definition as definiti3_1_2_,
    card1_.transcription as transcri4_1_2_,
    card1_.type_id as type_id5_1_2_
from card_mappings cardmappin0_
left outer join cards card1_ on cardmappin0_.dest_card_id=card1_.id
where cardmappin0_.source_card_id=1;

-- 2019-07-19 15:40:46,747 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([id1_0_1_] : [BIGINT]) - [8]
-- 2019-07-19 15:40:46,747 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([id1_1_2_] : [BIGINT]) - [4]
-- 2019-07-19 15:40:46,747 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([dest_car3_0_1_] : [BIGINT]) - [4]
-- 2019-07-19 15:40:46,747 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([frequenc2_0_1_] : [DOUBLE]) - [1.0]
-- 2019-07-19 15:40:46,748 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([source_c4_0_1_] : [BIGINT]) - [1]
-- 2019-07-19 15:40:46,748 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([content2_1_2_] : [VARCHAR]) - [car]
-- 2019-07-19 15:40:46,748 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([definiti3_1_2_] : [VARCHAR]) - [a road vehicle, typically with four wheels, powered by an internal combustion engine and able to carry a small number of people.]
-- 2019-07-19 15:40:46,748 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([transcri4_1_2_] : [VARCHAR]) - [null]
-- 2019-07-19 15:40:46,748 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([type_id5_1_2_] : [BIGINT]) - [5]
-- 2019-07-19 15:40:46,748 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([source_c4_0_0_] : [BIGINT]) - [1]
-- 2019-07-19 15:40:46,749 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([id1_0_0_] : [BIGINT]) - [8]
-- 2019-07-19 15:40:46,749 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([id1_0_1_] : [BIGINT]) - [9]
-- 2019-07-19 15:40:46,749 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([id1_1_2_] : [BIGINT]) - [7]
-- 2019-07-19 15:40:46,749 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([dest_car3_0_1_] : [BIGINT]) - [7]
-- 2019-07-19 15:40:46,749 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([frequenc2_0_1_] : [DOUBLE]) - [0.8]
-- 2019-07-19 15:40:46,749 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([source_c4_0_1_] : [BIGINT]) - [1]
-- 2019-07-19 15:40:46,750 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([content2_1_2_] : [VARCHAR]) - [auto]
-- 2019-07-19 15:40:46,750 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([definiti3_1_2_] : [VARCHAR]) - [a car]
-- 2019-07-19 15:40:46,750 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([transcri4_1_2_] : [VARCHAR]) - [null]
-- 2019-07-19 15:40:46,750 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([type_id5_1_2_] : [BIGINT]) - [5]
-- 2019-07-19 15:40:46,750 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([source_c4_0_0_] : [BIGINT]) - [1]
-- 2019-07-19 15:40:46,750 TRACE [descriptor.sql.BasicExtractor, extract] extracted value ([id1_0_0_] : [BIGINT]) - [9]
-- 2019-07-19 15:40:46,751 TRACE [CollectionType, getCollection] Created collection wrapper: [edu.hibernate.samples.aveng.entity.Card.cardMappings#4]
-- 2019-07-19 15:40:46,751 TRACE [CollectionType, getCollection] Created collection wrapper: [edu.hibernate.samples.aveng.entity.Card.cardMappings#7]