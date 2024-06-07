CREATE TABLE IF NOT EXISTS competences (
    id SERIAL,
    name TEXT,
    description TEXT,
    CONSTRAINT pk_competences PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS projets (
    id SERIAL,
    name TEXT,
    description TEXT,
    CONSTRAINT pk_projets PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS competences_in_projets (
    competence_id INT,
    projet_id INT,
    CONSTRAINT pk_competences_in_projets PRIMARY KEY (competence_id, projet_id),
    CONSTRAINT fk_competences_in_projets_competence_id FOREIGN KEY (competence_id) REFERENCES competences(id) ON DELETE CASCADE ,
    CONSTRAINT fk_competences_in_projets_projet_id FOREIGN KEY (projet_id) REFERENCES projets(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS jobs (
    id SERIAL,
    name TEXT,
    description TEXT,
    entreprise TEXT,
    date_debut DATE,
    date_fin DATE,
    CONSTRAINT pk_jobs PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS admin (
    id SERIAL,
    mail TEXT UNIQUE NOT NULL CHECK (mail ~* '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
    password TEXT NOT NULL,
    firstname TEXT DEFAULT 'unknown',
    lastname TEXT DEFAULT 'unknown',
    CONSTRAINT pk_admin PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS message (
    id SERIAL,
    name TEXT NOT NULL,
    mail TEXT NOT NULL CHECK (mail ~* '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
    message TEXT NOT NULL,
    repondu BOOLEAN DEFAULT FALSE,
    CONSTRAINT pk_messages PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS study (
    id SERIAL,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    lieu TEXT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    CONSTRAINT pk_study PRIMARY KEY (id)
);