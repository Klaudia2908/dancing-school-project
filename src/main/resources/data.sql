-- role
INSERT INTO S_UPRAWNIENIA(ID, NAZWA)
VALUES (1, 'ADMIN');
INSERT INTO S_UPRAWNIENIA(ID, NAZWA)
VALUES (2, 'USER');
INSERT INTO S_UPRAWNIENIA(ID, NAZWA)
VALUES (3, 'EMPLOYEE');

INSERT INTO S_TYP_OSOBY(ID, NAZWA)
VALUES(1, 'CLIENT');

INSERT INTO USERS(ID, LOGIN, HASLO, ID_S_UPRAWNIENIA)
VALUES (1, 'zachu', '$2a$10$lXojkbR.q2zWWqlTgjQMUu1EZu7TD.vBZ03VC.ycrcxft2sr7qVmG', 1);