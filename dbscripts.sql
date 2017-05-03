CREATE TABLE expencesTable (
    id bigserial NOT NULL PRIMARY KEY,
    data DATE NOT NULL default CURRENT_DATE
    produs character(300) NOT NULL,
    cantitate INTEGER,
    pret INTEGER,
    categorie character(40)
);

INSERT INTO expencesTable(data, produs, cantitate ,pret, categorie) VALUES ('23.04.2017','paine',1,3,'food');


CREATE TABLE expencesTable (
    id bigserial NOT NULL PRIMARY KEY,
    data DATE NOT NULL default CURRENT_DATE,
    produs character(300) NOT NULL,
    cantitate INTEGER,
    pret INTEGER,
    categorie character(40)
);
commit;