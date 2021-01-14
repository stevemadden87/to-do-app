CREATE TABLE users (
	id          UUID PRIMARY KEY,
	username	TEXT NOT NULL,
	password	TEXT NOT NULL	 
);

CREATE TABLE todo (
	id          UUID PRIMARY KEY,
	description	TEXT NULL,
	user_id  UUID NOT NULL REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE,
	is_done BOOLEAN NOT NULL,
	last_updated DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE role (
	id          UUID PRIMARY KEY,
	name	    TEXT NOT NULL	 
);




