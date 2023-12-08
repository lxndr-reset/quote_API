CREATE TABLE IF NOT EXISTS app_user
(
    email varchar(128) PRIMARY KEY NOT NULL,
    name VARCHAR(64) NOT NULL,
    password VARCHAR(72) NOT NULL,
    creation_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS quote
(
    id bigint auto_increment PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    rate integer NOT NULL,
    edition_date timestamp NOT NULL,
    user_email varchar(128) NOT NULL
);

alter table quote
add foreign key (user_email)
references app_user(email);