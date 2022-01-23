--                                                                                                  role
INSERT INTO S_UPRAWNIENIA(ID, NAZWA)
VALUES (1, 'ADMIN');
INSERT INTO S_UPRAWNIENIA(ID, NAZWA)
VALUES (2, 'USER');
INSERT INTO S_UPRAWNIENIA(ID, NAZWA)
VALUES (3, 'EMPLOYEE');

--                                                                                                  słownik osób
INSERT INTO S_TYP_OSOBY(ID, NAZWA)
VALUES (1, 'CLIENT');

--                                                                                                  użytkownicy
INSERT INTO USERS(ID, LOGIN, HASLO, ID_S_UPRAWNIENIA)
VALUES (1, 'kjastr', '$2a$10$lXojkbR.q2zWWqlTgjQMUu1EZu7TD.vBZ03VC.ycrcxft2sr7qVmG', 1);

--                                                                                                  szkoły
INSERT INTO SZKOLY(ID, NAZWA, OPIS)
VALUES (1, 'Szkoła Tańca Riviera', 'Opis Szkoły Tańca Riviera');
INSERT INTO SZKOLY(ID, NAZWA, OPIS)
VALUES (2, 'Fantastyczna Szkoła Pantofelek', 'Opis Fantastycznej Szkoły Pantofelek');

--                                                                                                  szkoły adresy
INSERT INTO ADRES_SZKOLY(ID, ULICA, NR_BUDYNKU, NR_LOKALU, MIEJSCOWOSC, KOD_POCZTOWY, ID_SZKOLY, IDENTYFIKATOR)
VALUES (1, 'Chmielna', '1', '1', 'Warszawa', '02-646', 1,'rivierachmielna');
INSERT INTO ADRES_SZKOLY(ID, ULICA, NR_BUDYNKU, NR_LOKALU, MIEJSCOWOSC, KOD_POCZTOWY, ID_SZKOLY, IDENTYFIKATOR)
VALUES (2, 'Taneczna', '2', '2', 'Lublin', '03-222', 1,'rivierataneczna');
INSERT INTO ADRES_SZKOLY(ID, ULICA, NR_BUDYNKU, NR_LOKALU, MIEJSCOWOSC, KOD_POCZTOWY, ID_SZKOLY, IDENTYFIKATOR)
VALUES (3, 'Kwadratowa', '3', '3', 'Radom', '05-111', 2, 'pantofelekkwadratowa');
INSERT INTO ADRES_SZKOLY(ID, ULICA, NR_BUDYNKU, NR_LOKALU, MIEJSCOWOSC, KOD_POCZTOWY, ID_SZKOLY, IDENTYFIKATOR)
VALUES (4, 'Poetycka', '4', '4', 'Warszawa', '22-673', 2, 'pantofelekpoetycka');