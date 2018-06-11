INSERT INTO USERS (username, password) VALUES ('admin', '$2a$04$Wr0vaXk7CBcidxulb1TBs.GaLnpppAP6KW3KZ.qLPCoXAsU.xwhPa');
INSERT INTO USERS (username, password) VALUES ('user', '$2a$04$zo3Zdf9dNpkRrdqA90px/uuwoH7d0kmJCX.Lk3T.S1Dhke9kEVTiK');

INSERT INTO ROLES (username, rolename) VALUES ('admin', 'ROLE_USER');
INSERT INTO ROLES (username, rolename) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO ROLES (username, rolename) VALUES ('user', 'ROLE_USER');

INSERT INTO CREDIT_CARDS (number, name, expiration_month, expiration_year, owner_username) VALUES ('4111111111111111', 'TEST USER 1', 10, 2020, 'admin');
INSERT INTO CREDIT_CARDS (number, name, expiration_month, expiration_year, owner_username) VALUES ('5105105105105100', 'TEST USER 2', 3, 2022, 'user');