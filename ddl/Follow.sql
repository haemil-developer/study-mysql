create table Follow
(
    id int auto_increment,
    fromMemberId int not null,
    toMemberId int not null,
    createdAt datetime not null,
    constraint Follow_id_uindex primary key (id)
);

create unique index Follow_fromMemberId_toMemberId_uindex on Follow (fromMemberId, toMemberId);
