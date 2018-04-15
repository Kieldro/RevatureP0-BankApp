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

DROP TABLE user_account;