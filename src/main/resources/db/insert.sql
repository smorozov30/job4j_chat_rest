INSERT INTO role (name) VALUES ('USER');
INSERT INTO role (name) VALUES ('ADMIN');

INSERT INTO person (login, password, role_id) VALUES ('root@root', '$2a$10$TX7b0baht8v8AjfXQVrES./0f2HDgB0RfCUQyMr8fIpuRU7rYJlrS', 1);
INSERT INTO person (login, password, role_id) VALUES ('user@user', '$2a$10$TX7b0baht8v8AjfXQVrES./0f2HDgB0RfCUQyMr8fIpuRU7rYJlrS', 2);

INSERT INTO room (name) VALUES ('Junior Room');
INSERT INTO room (name) VALUES ('Middle Room');

INSERT INTO message (text, created, room_id, author_id) VALUES ('Hello!', NOW(), 1, 1);
INSERT INTO message (text, created, room_id, author_id) VALUES ('How are you?', NOW(), 1, 1);
INSERT INTO message (text, created, room_id, author_id) VALUES ('Bay-bay!', NOW(),1, 2);
INSERT INTO message (text, created, room_id, author_id) VALUES ('Wow!', NOW(), 2, 2);