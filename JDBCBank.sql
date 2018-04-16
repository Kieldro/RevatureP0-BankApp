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
	

CREATE TABLE user_account (
    name       VARCHAR2(40),
    balance    FLOAT,
    admin      INT,
    approved   INT,
    CONSTRAINT pk_name PRIMARY KEY ( name )
);

--test commands
SELECT * FROM user_account;
INSERT INTO user_account VALUES ('Jane', 6.66, 0, 0);commit;        -- dummy row used for testing
INSERT INTO user_account VALUES ('Ian', 123.45, 1, 1);


	UPDATE user_account SET balance = 4.0, approved = 0 WHERE name = 'Ian B';
    
DELETE FROM user_account WHERE name = 'Ian B';	
        
        
    commit;



--Create a Sequence to generate the value for the Pokemon PK's
CREATE SEQUENCE pokemon_sequence
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
