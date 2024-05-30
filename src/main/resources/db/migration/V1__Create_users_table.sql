CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    created DATE,
    modified DATE,
    last_login DATE,
    token VARCHAR(2048),
    is_active BOOLEAN
);