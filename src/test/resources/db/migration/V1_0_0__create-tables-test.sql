CREATE TABLE IF NOT EXISTS roles
(
    id        SERIAL  NOT NULL PRIMARY KEY,
    role_type      VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users
(
    id           SERIAL  NOT NULL PRIMARY KEY,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    username     VARCHAR(255),
    password     VARCHAR(255),
    email        VARCHAR(255),
    address      VARCHAR(255),
    average_grade FLOAT,
    role_id       INT  NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS grades
(
    id         SERIAL  NOT NULL PRIMARY KEY,
    grade      INT,
    reviewer_id INT  NOT NULL,
    reviewee_id INT  NOT NULL,
    FOREIGN KEY (reviewer_id) REFERENCES users (id),
    FOREIGN KEY (reviewee_id) REFERENCES users (id)
);
