create table Pronunciation
(
    id bigint not null,
    transcription varchar(255),
    phone_id bigint,
    primary key (id)
);

create table Word
(
    id bigint not null,
    content varchar(255),
    primary key (id)
);

alter table Pronunciation
add constraint FKkjmirr3oqh4xgwwswwcnkl9yu foreign key (phone_id) references Word;

insert into Word
(content, id)
values
('trigger', 1);

insert into Pronunciation
(transcription, phone_id, id)
values
('[''trɪɡə]', 1, 2);

insert into Word
(content, id)
values
('test', 3);

insert into Pronunciation
(transcription, phone_id, id)
values
('[test]', 3, 4);

-- orphan
delete from Pronunciation
where id=2;

-- cascaded

delete from Pronunciation
where id=4;

delete from Word
where id=3;