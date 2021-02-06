INSERT INTO role (name) VALUES ('USER');
INSERT INTO role (name) VALUES ('ADMIN');

INSERT INTO person (name, lastname, role_id) VALUES ('Sergey', 'Morozov', 1);
INSERT INTO person (name, lastname, role_id) VALUES ('Ivan', 'Ivanov', 2);

INSERT INTO room (name) VALUES ('Junior Room');
INSERT INTO room (name) VALUES ('Middle Room');

INSERT INTO message (text, created, room_id, author_id) VALUES ('Hello!', NOW(), 1, 1);
INSERT INTO message (text, created, room_id, author_id) VALUES ('How are you?', NOW(), 1, 1);
INSERT INTO message (text, created, room_id, author_id) VALUES ('Bay-bay!', NOW(),1, 2);
INSERT INTO message (text, created, room_id, author_id) VALUES ('Wow!', NOW(), 2, 2);