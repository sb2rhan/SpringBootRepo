CREATE TABLE IF NOT EXISTS users (
    id int8 not null,
    username varchar(128) not null unique,
    password varchar(256) not null,
    age int4,
    primary key (id)
);