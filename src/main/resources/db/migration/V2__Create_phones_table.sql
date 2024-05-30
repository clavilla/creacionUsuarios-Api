CREATE TABLE phones (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(255),
    city_code VARCHAR(255),
    country_code VARCHAR(255),
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id)
);