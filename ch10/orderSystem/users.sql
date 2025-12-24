CREATE TABLE users (
	user_name VARCHAR(15) NOT NULL PRIMARY KEY,
	user_pass VARCHAR(15) NOT NULL
);

CREATE TABLE user_roles (
	user_name VARCHAR(15) NOT NULL,
	role_name VARCHAR(15) NOT NULL,
	PRIMARY KEY(user_name, role_name)
);

INSERT INTO users VALUES ('admin', '1111'), ('manager', '1111'), ('user', '1111');

INSERT INTO user_roles VALUES ('admin', 'ROLE_ADMIN'), ('manager', 'ROLE_MANAGER'), ('user', 'ROLE_USER');