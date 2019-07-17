create table Pronunciation
(
    id bigint not null,
    transcription varchar(255),
    primary key (id)
);

create table Word
(
    id bigint not null,
    content varchar(255),
    pron_id bigint,
    primary key (id)
);

alter table Word
add constraint UK_3unmrfg3vatrneeyxa42mxqxs unique (pron_id);

alter table Word
add constraint PRON_ID_FK foreign key (pron_id) references Pronunciation;

insert into Pronunciation
(transcription, id)
values
('[''trɪɡə]', 2);

insert into  Word
(content, pron_id, id)
values
('trigger', 2, 1);

delete from Word
where id=1;

delete from Pronunciation
where id=2;