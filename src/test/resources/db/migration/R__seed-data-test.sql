INSERT INTO roles (id, role_type)
VALUES (1, 'HOST');

INSERT INTO roles (id, role_type)
VALUES (2, 'GUEST');

INSERT INTO users (id, first_name, last_name, username, password, email, address, average_grade, role_id)
VALUES (2, 'John', 'Doe', 'john.doe', 'password', 'john.doe@mail.com', '1234 Elm St', 2.0, 1);