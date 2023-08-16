create table Post
(
    id int auto_increment,
    memberId int not null,
    contents varchar(100) not null,
    createdDate date not null,
    createdAt datetime not null,
    constraint Post_id_uindex primary key (id)
);

create index Post__index_member_id on Post (memberId);

create index Post__index_created_date on Post (createdDate);

create index Post__index_created_date_member_id on Post (createdDate, memberId);