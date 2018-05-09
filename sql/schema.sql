CREATE TABLE heating (
    id BIGSERIAL PRIMARY KEY,
    host VARCHAR(36) NOT NULL,
    port INTEGER NOT NULL,
    description VARCHAR(36) NOT NULL
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(36) NOT NULL,
    password VARCHAR(36) NOT NULL,
    full_name VARCHAR(36) NOT NULL,
    user_role VARCHAR(16) NOT NULL
);

CREATE TABLE users_heating (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users (id),
    heating_id BIGINT REFERENCES heating (id)
);