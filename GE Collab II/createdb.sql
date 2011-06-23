create database if not exists workspace_db;
use workspace_db;
-- tables in genspace

DROP TABLE IF EXISTS registration;
CREATE TABLE registration (
  id int(11) NOT NULL auto_increment,
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  email varchar(50) default NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  work_title varchar(50) default NULL,
  phone varchar(50) default NULL,
  lab_affiliation varchar(100) NOT NULL,
  addr1 varchar(50) default NULL,
  addr2 varchar(50) default NULL,
  city varchar(50) default NULL,
  state varchar(50) default NULL,
  zipcode varchar(7) default NULL,
  online_status int(11) default NULL,
  interests text,
  createdat datetime default NULL,
  datavisibility int(11) default NULL,
  logdata int(11) default NULL,
  PRIMARY KEY  (id),
  UNIQUE registration_username_lookup (username)
) engine=innodb;

DROP TABLE IF EXISTS Friend;
CREATE TABLE Friend (
  id_1 int(11) NOT NULL,
  id_2 int(11) NOT NULL,
  mutual bit(1) NOT NULL,
  visible bit(1) NOT NULL,
  FOREIGN KEY (id_2) references registration(id) on delete cascade,
  FOREIGN KEY (id_1) references registration(id) on delete cascade
) engine=innodb;

DROP TABLE IF EXISTS Network;
CREATE TABLE Network (
  id int(11) NOT NULL auto_increment,
  name varchar(255) NOT NULL,
  owner int(11) NOT NULL,
  PRIMARY KEY  (id),
  FOREIGN KEY (owner) references registration(id) on delete cascade
) engine=innodb;

DROP TABLE IF EXISTS User_Network;
CREATE TABLE User_Network (
  user_id int(11) NOT NULL,
  network_id int(11) NOT NULL,
  verified bit(1) default NULL,
  visible bit(1) NOT NULL,
  constraint usernet_unique_network unique (user_id, network_id),
  FOREIGN KEY (user_id) references registration(id) on delete cascade,
  FOREIGN KEY (network_id) references network(id) on delete cascade
) engine=innodb;




-- tables in workspace_db

create table if not exists access (
       id     int(11) not null auto_increment primary key,
       name   varchar(50)
) engine=innodb;

create table if not exists workspace (
       id             int not null auto_increment primary key,
       title	      varchar(200),
       creator        int(11),
       description    varchar(1000),
       location       varchar(1000),
       createdAt      timestamp,
       version        int, 
       lastSync       timestamp,
       locked         boolean,
       lastLockedUser int,
       foreign key (creator) references registration(id) on delete cascade
) engine=innodb;

create table if not exists workspace_user (
       id     int not null auto_increment primary key,
       wspid  int,
       uid    int(11),
       gid    int(11),
       constraint wspuser_unique_access unique (wspid, uid),
       foreign key (wspid) references workspace(id) on delete cascade,
       foreign key (uid) references registration(id) on delete cascade,
       foreign key (gid) references access(id) on delete cascade
) engine=innodb;

create table if not exists annotation (
       id         int not null auto_increment primary key,
       wspid      int,
       annotation varchar(1000),
       creator    int(11),
       createdAt  timestamp,
       foreign key (wspid) references workspace(id) on delete cascade,
       foreign key (creator) references registration(id) on delete cascade
) engine=innodb;

create table if not exists history (
       id         int not null auto_increment primary key,
       wspid      int,
       uid	  int(11),
       type	  char,
       accessedAt timestamp,
       foreign key (wspid) references workspace(id) on delete cascade,
       foreign key (uid) references registration(id) on delete cascade
) engine=innodb;

create table if not exists settings (
       id    	    int not null auto_increment primary key,
       name    	    varchar(500),
       value	    varchar(1000)
) engine=innodb;


-- dummy data

insert into registration(username, password, first_name, last_name, lab_affiliation, online_status)
       values ('dba', 'admin', 'Database', 'Admin', 'c2b2', TRUE),
       	      ('guest', '', 'Guest', 'User', 'c2b2', TRUE);

insert into access (name)
       values ('read'),
       	      ('write'),
	      ('admin'),
	      ('owner');

insert into network (name, owner)
       values('c2b2', 1);

insert into user_network(user_id, network_id, visible)
       values (1, 1, 1),
       	      (2, 1, 1);

insert into settings(name, value) 
       values('workspace_root', 'C:/Docume~1/mw2518.CGC/axis2-1.5.3/samples/testdld/WorkspaceServiceResources/');
