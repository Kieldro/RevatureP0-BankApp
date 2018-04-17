SELECT * FROM user_account;
	
DROP TABLE user_account;
CREATE TABLE user_account (
--    user_id     int,
    name       VARCHAR2(40),
    password   VARCHAR2(40) default 'asd',
    balance    number (13,2)default 0,
    admin      INT default 0,
    approved   INT default 0,
    CONSTRAINT pk_name PRIMARY KEY ( name )
--    , CONSTRAINT UK_user_id UNIQUE (user_id)
);

--test commands

TRUNCATE TABLE user_account;
INSERT INTO user_account VALUES ('Jane','asd', 6.66, 0, 0);        -- dummy row used for testing
INSERT INTO user_account VALUES ('keo','asd', 0, 1, 1);
commit;

SELECT * FROM user_account;

INSERT INTO user_account (name) VALUES ('Ian');
DELETE FROM user_account WHERE name = 'Ian';	

UPDATE user_account SET balance = 4.0, approved = 0 WHERE name = 'Ian B';
    
        
        
    commit;




--Create a Sequence to generate the value for the user id's
CREATE SEQUENCE user_id_sequence
    START WITH 1 
    INCREMENT BY 1
    MAXVALUE 900
    MINVALUE -1
    NOCACHE;

--Create an INSERT trigger on Pokemon to auto-increment the PK
CREATE OR REPLACE TRIGGER before_insert_pokemon
BEFORE INSERT ON pokemon
FOR EACH ROW
BEGIN
    IF :NEW.id IS NULL THEN 
        SELECT pokemon_sequence.nextval INTO :NEW.id FROM dual;
    END IF;
END;
/


--Create a Stored Procedure to INSERT a new Pokemon
CREATE OR REPLACE PROCEDURE insert_pokemon(pokedexEntry IN INTEGER,
                name IN VARCHAR2, type1 IN VARCHAR2, type2 IN VARCHAR2, generation IN INTEGER)
AS
BEGIN
    
    INSERT INTO pokemon VALUES(null, pokedexEntry, name, type1, type2, generation);
    COMMIT;
END;
/
-- Park's tables
CREATE TABLE User_Customer
(
    Customer_Id INTEGER,
    First_Name VARCHAR2 (160),
   Last_Name VARCHAR2 (160),
    User_Name VARCHAR2 (160),
   User_Password VARCHAR2 (160),
    Approval INTEGER DEFAULT 0,
    
   CONSTRAINT Unique_User UNIQUE (User_Name),
    CONSTRAINT PK_User_Customer PRIMARY KEY (Customer_Id)    
);

CREATE TABLE User_Account
(
    Account_Id INTEGER,
    Account_Name VARCHAR2 (160),
    User_Name VARCHAR2 (160),
    Acct_Creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Balance NUMBER DEFAULT 0,

    CONSTRAINT PK_User_Account PRIMARY KEY (Account_Id),
   CONSTRAINT FK_Customer_User FOREIGN KEY (User_Name)
   REFERENCES User_Customer(User_Name)
);
