CREATE TABLE IF NOT EXISTS profiles (
    id          int8         not null,
    description varchar(512) not null,
    user_id     int8         not null,
    primary key (id),
    constraint fk_profile_user foreign key (user_id) references users (id)
);