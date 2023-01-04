--DROP TABLE IF EXISTS phone;
--DROP TABLE IF EXISTS user;

CREATE TABLE user (
                       id VARCHAR(100) PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_at DATETIME NOT NULL,
                       modified_at DATETIME NOT NULL,
                       last_login DATETIME NOT NULL,
                       is_active BOOLEAN NOT NULL,
                       token VARCHAR(255) NOT NULL,
                       token_expiration DATETIME NOT NULL,
                       UNIQUE (email)
);

CREATE TABLE phone (
                             id VARCHAR(100) PRIMARY KEY,
                             user_id VARCHAR(100) NOT NULL,
                             number VARCHAR(100) NOT NULL,
                             city_code VARCHAR(100) NOT NULL,
                             country_code VARCHAR(100) NOT NULL,
                             FOREIGN KEY (user_id) REFERENCES user(id)
);