DROP TABLE IF EXISTS competences CASCADE ;
DROP TABLE IF EXISTS projets CASCADE ;
DROP TABLE IF EXISTS competences_in_projets CASCADE ;
DROP TABLE IF EXISTS admin;

CREATE TABLE competences (
    id SERIAL,
    name TEXT,
    description TEXT,
    CONSTRAINT pk_competences PRIMARY KEY (id)
);

CREATE TABLE projets (
    id SERIAL,
    name TEXT,
    description TEXT,
    CONSTRAINT pk_projets PRIMARY KEY (id)
);

CREATE TABLE competences_in_projets (
    competence_id INT,
    projet_id INT,
    CONSTRAINT pk_competences_in_projets PRIMARY KEY (competence_id, projet_id),
    CONSTRAINT fk_competences_in_projets_competence_id FOREIGN KEY (competence_id) REFERENCES competences(id) ON DELETE CASCADE ,
    CONSTRAINT fk_competences_in_projets_projet_id FOREIGN KEY (projet_id) REFERENCES projets(id) ON DELETE CASCADE
);

CREATE TABLE admin (
    id SERIAL,
    mail TEXT,
    password TEXT,
    CONSTRAINT pk_admin PRIMARY KEY (id)
);