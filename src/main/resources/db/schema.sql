CREATE TABLE role (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(2000)
);

CREATE TABLE person (
    id SERIAL PRIMARY KEY NOT NULL,
    login VARCHAR(2000),
    password VARCHAR(2000),
    role_id INT NOT NULL DEFAULT 1,
    FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE room (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(2000)
);

CREATE TABLE message (
    id SERIAL PRIMARY KEY NOT NULL,
    text VARCHAR(2000),
    created TIMESTAMP NOT NULL DEFAULT NOW(),
    room_id INT,
    author_id INT,
    FOREIGN KEY (room_id) REFERENCES room (id),
    FOREIGN KEY (author_id) REFERENCES person (id)
);