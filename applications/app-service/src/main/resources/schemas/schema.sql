CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255),
                       password VARCHAR(255),
                       created TIMESTAMP,
                       modified TIMESTAMP,
                       last_login TIMESTAMP,
                       token VARCHAR(255),
                       is_active BOOLEAN
);

CREATE TABLE phones (
                        id BIGSERIAL PRIMARY KEY,
                        number VARCHAR(255),
                        citycode VARCHAR(255),
                        contrycode VARCHAR(255),
                        user_id UUID REFERENCES users(id) ON DELETE CASCADE
);