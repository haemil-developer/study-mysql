create table Member
(
    id int auto_increment,
    email varchar(20) not null,
    nickname varchar(20) not null,
    birthday date not null,
    createdAt datetime not null,
    constraint Member_id_uindex primary key (id)
);

create table MemberNicknameRecord
(
    id int auto_increment,
    memberId int not null,
    nickname varchar(20) not null,
    createdAt datetime not null,
    constraint MemberNicknameRecord_id_uindex primary key (id)
);