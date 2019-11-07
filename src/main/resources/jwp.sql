DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS ( 
	userId          varchar(12)		NOT NULL, 
	password		varchar(12)		NOT NULL,
	name			varchar(20)		NOT NULL,
	email			varchar(50),	
  	
	PRIMARY KEY               (userId)
);

INSERT INTO USERS VALUES('admin', 'password', '자바지기', 'admin@slipp.net');
INSERT INTO USERS VALUES('seungah', '1234', '승아', '1234@slipp.net');
INSERT INTO USERS VALUES('yonghyun', '4321', '용현', '4321@slipp.net');