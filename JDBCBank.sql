SELECT * FROM user_account;
	
DROP TABLE user_account;
CREATE TABLE user_account (
--    user_id     int,
    name       VARCHAR2(40),
    password   VARCHAR2(40) default 'asd',
    balance    FLOAT default 0,
    admin      INT default 0,
    approved   INT default 0,
    CONSTRAINT pk_name PRIMARY KEY ( name ),
    CONSTRAINT UK_user_id UNIQUE (user_id)
);
--Create a Sequence to generate the value for the user id's
CREATE SEQUENCE user_id_sequence
    START WITH 1 
    INCREMENT BY 1
    MAXVALUE 900
    MINVALUE -1
    NOCACHE;

--test commands
SELECT * FROM user_account;
INSERT INTO user_account VALUES ('Jane','asd', 6.66, 0, 0);        -- dummy row used for testing
commit;
INSERT INTO user_account (name) VALUES ('Ian');
DELETE FROM user_account WHERE name = 'Ian';	

	UPDATE user_account SET balance = 4.0, approved = 0 WHERE name = 'Ian B';
    
        
        
    commit;





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
