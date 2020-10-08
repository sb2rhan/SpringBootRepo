CREATE TABLE IF NOT EXISTS messages (
    id         int8    not null,
    content    varchar not null,
    date       date    not null,
    profile_id int8    not null,
    primary key (id),
    constraint fk_message_profile foreign key (profile_id) references profiles (id)
);