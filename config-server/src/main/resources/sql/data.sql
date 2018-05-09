INSERT INTO users VALUES (1, 'Stanislav Vorozhka', 'admin', 'admin', 'ADMIN');
INSERT INTO users VALUES (2, 'Ivan Ivanov', 'user', 'user', 'USER');

INSERT INTO heating VALUES (1, 'test room1', 'localhost', 10001);
INSERT INTO heating VALUES (2, 'test room2', 'localhost', 10002);

INSERT INTO users_heating (heating_id, user_id) VALUES (1, 1);