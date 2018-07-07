

DROP DATABASE IF EXISTS mvc;
DROP TABLE IF EXISTS mvc.users;
DROP TABLE IF EXISTS mvc.userType;


CREATE DATABASE IF NOT EXISTS mvc; 



CREATE TABLE IF NOT EXISTS userType(
  id TINYINT AUTO_INCREMENT,
  typeName VARCHAR(100) NOT NULL,
  displayName VARCHAR(100) NOT NULL,
  deleteFlag TINYINT(1) NOT NULL DEFAULT '0',
  CONSTRAINT PK_userType_id PRIMARY KEY (id),  
  CONSTRAINT UNIQ_userType_typeName UNIQUE (typeName),
  CONSTRAINT UNIQ_userType_displayName UNIQUE (displayName),
  INDEX IDX_userType_deleteFlag (deleteFlag)
) ENGINE INNODB;

INSERT INTO mvc.userType(id,typeName,displayName) VALUES('1','root','Root');
INSERT INTO mvc.userType(id,typeName,displayName) VALUES('2','admin','Admin');
INSERT INTO mvc.userType(id,typeName,displayName) VALUES('3','client','Client');


CREATE TABLE IF NOT EXISTS mvc.users (
  id INT AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL,
  userpwd TEXT,
  userType TINYINT NOT NULL DEFAULT '3',
  createdBy INT NOT NULL DEFAULT '1',
  userStatus TINYINT NOT NULL DEFAULT '1',
  deleteFlag TINYINT(1) NOT NULL DEFAULT '0',
  CONSTRAINT PK_users_id PRIMARY KEY (id),
  CONSTRAINT UNIQ_users_username UNIQUE (username) ,
  CONSTRAINT FKSLF__users_createdBy__id FOREIGN KEY (createdBy) REFERENCES mvc.users(id) ,
  CONSTRAINT FK__users_userType__userType_id FOREIGN KEY (userType) REFERENCES mvc.userType(id),
  INDEX IDX_users_usertype (usertype) ,
  INDEX IDX_users_userstatus (userstatus) ,
  INDEX IDX_users_deleteFlag (deleteFlag)
) ENGINE INNODB;


INSERT INTO mvc.users (username, userpwd, usertype) VALUES ('root', 'root', 1); 
INSERT INTO mvc.users (username, userpwd, usertype) VALUES ('admin@admin.com', 'admin', 2); 
INSERT INTO mvc.users (username, userpwd, createdBy) VALUES ('jb@jb.com', 'jb', 2); 
