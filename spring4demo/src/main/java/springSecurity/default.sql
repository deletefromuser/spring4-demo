DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
create table users(
	username varchar(50) not null primary key,
	password varchar(100) not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

DROP TABLE IF EXISTS group_members;
DROP TABLE IF EXISTS group_authorities;
DROP TABLE IF EXISTS groups;
create table groups (
	id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	group_name varchar(50) not null
);

create table group_authorities (
	group_id bigint not null,
	authority varchar(50) not null,
	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table group_members (
	id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
	username varchar(50) not null,
	group_id bigint not null,
	constraint fk_group_members_group foreign key(group_id) references groups(id)
);


-- insert into users(username, password, enabled) values("tom", 1, true), ("jack", 2, true);
-- insert into authorities(username, authority) values("tom", "ROLE_ADMIN"), ("jack", "ROLE_USER");

-- select * from users
-- select * from authorities