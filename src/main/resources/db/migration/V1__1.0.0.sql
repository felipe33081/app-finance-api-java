CREATE TABLE IF NOT EXISTS user_data (
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR NOT NULL UNIQUE,
    email    VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction_data (
    id           BIGSERIAL PRIMARY KEY,
    description  VARCHAR(255)   NOT NULL,
    date         DATE,
    amount       DECIMAL(10, 2) NOT NULL,
    paid         BOOLEAN,
    account_type INTEGER        NOT NULL,
    category     INTEGER        NOT NULL,
    user_id      BIGINT         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_data (id)
);

CREATE SEQUENCE IF NOT EXISTS user_data_seq;

ALTER TABLE user_data
    ALTER COLUMN id SET DEFAULT nextval('user_data_seq');