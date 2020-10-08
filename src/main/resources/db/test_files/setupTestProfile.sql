CREATE TABLE IF NOT EXISTS users
(
    id       int8         not null,
    username varchar(128) not null unique,
    password varchar(256) not null,
    age      int4,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS profiles
(
    id          int8         not null,
    description varchar(512) not null,
    user_id     int8         not null,
    primary key (id),
    constraint fk_profile_user foreign key (user_id) references users (id)
);

CREATE TABLE IF NOT EXISTS messages
(
    id         int8    not null,
    content    varchar not null,
    date       date    not null,
    profile_id int8    not null,
    primary key (id),
    constraint fk_message_profile foreign key (profile_id) references profiles (id)
);

CREATE TABLE IF NOT EXISTS authorities
(
    user_id   int8    not null,
    authority varchar not null,
    constraint fk_authority_user foreign key (user_id) references users (id)
);

INSERT INTO users
values (1, 'Mark', 'pass1232', 20);
INSERT INTO users
values (2, 'Fred', 'cheese23', 19);
INSERT INTO users
values (3, 'Men', 'veron232', 22);

INSERT INTO profiles
values (1, 'Profile of me', 1);
INSERT INTO profiles
values (2, 'My profile', 2);
INSERT INTO profiles
values (3, 'Check it out!', 3);

INSERT INTO messages
VALUES (1, 'Hello!', '2020-01-01', 1);
INSERT INTO messages
VALUES (2, 'How are you?', '2020-02-01', 2);
INSERT INTO messages
VALUES (3, 'What are you doing?', '2020-09-09', 3);

INSERT INTO authorities
VALUES (1, 'USER');
INSERT INTO authorities
VALUES (2, 'ADMIN');
INSERT INTO authorities
VALUES (3, 'USER');