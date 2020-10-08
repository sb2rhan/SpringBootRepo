CREATE TABLE IF NOT EXISTS authorities (
    user_id int8    not null,
    authority varchar not null,
    constraint fk_authority_user foreign key (user_id) references users (id)
);