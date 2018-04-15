SELECT
    *
FROM
    user_account;

CREATE TABLE user_account (
    name       VARCHAR2(40),
    balance    FLOAT,
    admin      INT,
    approved   INT,
    CONSTRAINT pk_name PRIMARY KEY ( name )
);

--test commands
INSERT INTO user_account VALUES ('Ian', 123.45, 1, 1);

SELECT * FROM user_account;

	UPDATE user_account SET balance = 4.0, approved = 0 WHERE name = 'Ian B';
	
DELETE FROM table_SELECT
    *
FROM
    user_account;

CREATE TABLE user_account (
    name       VARCHAR2(40),
    balance    FLOAT,
    admin      INT,
    approved   INT,
    CONSTRAINT pk_name PRIMARY KEY ( name )
);

--test commands
INSERT INTO user_account VALUES ('Ian', 123.45, 1, 1);

SELECT * FROM user_account;

	UPDATE user_account SET balance = 4.0, approved = 0 WHERE name = 'Ian B';
    
DELETE FROM user_account WHERE name = 'Ian B';	
        
        
    commit;



--DROP TABLE user_account;


name WHERE condition;	
        
        
    commit;



--DROP TABLE user_account;

