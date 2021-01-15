CREATE TABLE users (
	id          SERIAL PRIMARY KEY,
	username	TEXT NOT NULL,
	password	TEXT NOT NULL	 
);

CREATE TABLE todo (
	id          SERIAL PRIMARY KEY,
	description	TEXT NULL,
	user_id  INTEGER NOT NULL REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE,
	is_done BOOLEAN NOT NULL,
	last_updated DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE role (
	id          SERIAL PRIMARY KEY,
	name	    TEXT NOT NULL	 
);


CREATE TABLE user_role (
		  user_id INTEGER NOT NULL REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE,
		  role_id INTEGER NOT NULL REFERENCES  role(id) ON UPDATE CASCADE ON DELETE CASCADE
		);



