CREATE TABLE tb_users
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    balance NUMERIC(38, 2),
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE
);
