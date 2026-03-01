create table users(
    id varchar(100) NOT NULL,
    nis varchar(9) NOT NULL unique,
    name varchar(100) NOT NULL,
    password VARCHAR(100) not null,
    created_at TIMESTAMP ,
    updated_at TIMESTAMP ,
    PRIMARY KEY (id)
);

ALTER TABLE users
ADD COLUMN birth_date DATE;

ALTER TABLE users
    ADD COLUMN activated BOOLEAN ,
    ADD COLUMN activation_key VARCHAR(20),
    ADD COLUMN activation_date TIMESTAMP;