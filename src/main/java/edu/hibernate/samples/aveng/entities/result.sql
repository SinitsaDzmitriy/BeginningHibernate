create table Card
(
    id bigint not null,
    content varchar(255),
    definition varchar(255),
    lang varchar(255),
    transcription varchar(255),
    primary key (id)
);

create table CardMapping
(
    id bigint not null,
    frequency double,
    dest_card_id bigint,
    sourceCard_id bigint,
    primary key (id)
);

alter table CardMapping
add constraint UK_fs8ym4iw43d08a1au77iyf7i2
unique (dest_card_id);

alter table CardMapping
add constraint DEST_CARD_ID_FK
foreign key (dest_card_id) references Card;

alter table CardMapping
add constraint FK5vu3e6qn0l58m54rqa0dj98t
foreign key (sourceCard_id) references Card;

insert into Card
(content, definition, lang, transcription, id)
values
(
    'машина',
    'моторное дорожное транспортное средство, используемое для перевозки людей или грузов.',
    'ru',
    null,
    1
);

insert into Card
(content, definition, lang, transcription, id)
values
(
    'car',
    'a road vehicle, typically with four wheels, powered by an internal combustion engine and able to carry a small number of people.',
    'en',
    null,
    3
);

insert into CardMapping
(dest_card_id, frequency, sourceCard_id, id)
values
(
    3,
    1.0,
    1,
    2
);

insert into Card
(content, definition, lang, transcription, id)
values
(
    'auto',
    'a car.',
    'en',
    null,
    5
);

insert into CardMapping
(dest_card_id, frequency, sourceCard_id, id)
values
(
    5,
    0.8,
    1,
    4
);