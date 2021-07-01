--<ScriptOptions statementTerminator=";"/>

ALTER TABLE mobile_number DROP CONSTRAINT mobile-person;

ALTER TABLE service_person DROP CONSTRAINT service_person_service_fk;

ALTER TABLE substitution DROP CONSTRAINT substitution_person;

ALTER TABLE service_person DROP CONSTRAINT service_person_main_fk;

ALTER TABLE substitution DROP CONSTRAINT substitution_service_person;

ALTER TABLE email_account DROP CONSTRAINT email-person;

ALTER TABLE service_person DROP CONSTRAINT service_person_reserve_fk;

ALTER TABLE service DROP CONSTRAINT service_type_fk;

ALTER TABLE email_account DROP CONSTRAINT email_account_pkey;

ALTER TABLE person DROP CONSTRAINT person_pkey;

ALTER TABLE service_person DROP CONSTRAINT service_person_pkey;

ALTER TABLE substitution DROP CONSTRAINT substitution_pkey;

ALTER TABLE service_type DROP CONSTRAINT service_type_pkey;

ALTER TABLE service DROP CONSTRAINT service_pkey;

ALTER TABLE mobile_number DROP CONSTRAINT mobile-number_pkey;

DROP INDEX fki_service_person_fk;

DROP INDEX fki_service_person_reserve_fk;

DROP INDEX service_person_pkey;

DROP INDEX email_account_pkey;

DROP INDEX person_pkey;

DROP INDEX fki_absence_service;

DROP INDEX fki_service_person_main_fk;

DROP INDEX fki_service_type_fk;

DROP INDEX substitution_pkey;

DROP INDEX mobile-number_pkey;

DROP INDEX fki_absence_person;

DROP INDEX service_type_pkey;

DROP INDEX service_pkey;

DROP TABLE substitution;

DROP TABLE email_account;

DROP TABLE service_person;

DROP TABLE mobile_number;

DROP TABLE exception;

DROP TABLE service;

DROP TABLE service_type;

DROP TABLE person;

CREATE TABLE substitution (
		id INT4 NOT NULL,
		service_person_id INT4,
		person_id INT4
	);

CREATE TABLE email_account (
		id INT4 NOT NULL,
		person_id INT4,
		email_account VARCHAR(50)
	);

CREATE TABLE service_person (
		id INT4 NOT NULL,
		service_id INT4 NOT NULL,
		person_id INT4 NOT NULL,
		reserve_id INT4,
		can_go BOOL,
		have_gone BOOL,
		has_gone BOOL
	);

CREATE TABLE mobile_number (
		id INT4 NOT NULL,
		person_id INT4,
		mobile_number BPCHAR(11)
	);

CREATE TABLE exception (
		id INT4,
		person_id INT4,
		day DATE
	);

CREATE TABLE service (
		id INT4 NOT NULL,
		service_name VARCHAR(50),
		service_date DATE,
		service_type INT4 NOT NULL,
		eve BOOL,
		fixed BOOL
	);

CREATE TABLE service_type (
		id INT4 NOT NULL,
		service_type VARCHAR(2147483647)
	);

CREATE TABLE person (
		id INT4 NOT NULL,
		name VARCHAR(2147483647),
		comunidad INT4
	);

CREATE INDEX fki_service_person_fk ON service_person (service_id ASC);

CREATE INDEX fki_service_person_reserve_fk ON service_person (reserve_id ASC);

CREATE UNIQUE INDEX service_person_pkey ON service_person (id ASC);

CREATE UNIQUE INDEX email_account_pkey ON email_account (id ASC);

CREATE UNIQUE INDEX person_pkey ON person (id ASC);

CREATE INDEX fki_absence_service ON substitution (service_person_id ASC);

CREATE INDEX fki_service_person_main_fk ON service_person (person_id ASC);

CREATE INDEX fki_service_type_fk ON service (service_type ASC);

CREATE UNIQUE INDEX substitution_pkey ON substitution (id ASC);

CREATE UNIQUE INDEX mobile-number_pkey ON mobile_number (id ASC);

CREATE INDEX fki_absence_person ON substitution (person_id ASC);

CREATE UNIQUE INDEX service_type_pkey ON service_type (id ASC);

CREATE UNIQUE INDEX service_pkey ON service (id ASC);

ALTER TABLE email_account ADD CONSTRAINT email_account_pkey PRIMARY KEY (id);

ALTER TABLE person ADD CONSTRAINT person_pkey PRIMARY KEY (id);

ALTER TABLE service_person ADD CONSTRAINT service_person_pkey PRIMARY KEY (id);

ALTER TABLE substitution ADD CONSTRAINT substitution_pkey PRIMARY KEY (id);

ALTER TABLE service_type ADD CONSTRAINT service_type_pkey PRIMARY KEY (id);

ALTER TABLE service ADD CONSTRAINT service_pkey PRIMARY KEY (id);

ALTER TABLE mobile_number ADD CONSTRAINT mobile-number_pkey PRIMARY KEY (id);

ALTER TABLE mobile_number ADD CONSTRAINT mobile-person FOREIGN KEY (id)
	REFERENCES person (id);

ALTER TABLE service_person ADD CONSTRAINT service_person_service_fk FOREIGN KEY (service_id)
	REFERENCES service (id);

ALTER TABLE substitution ADD CONSTRAINT substitution_person FOREIGN KEY (person_id)
	REFERENCES person (id);

ALTER TABLE service_person ADD CONSTRAINT service_person_main_fk FOREIGN KEY (person_id)
	REFERENCES person (id);

ALTER TABLE substitution ADD CONSTRAINT substitution_service_person FOREIGN KEY (service_person_id)
	REFERENCES service_person (id);

ALTER TABLE email_account ADD CONSTRAINT email-person FOREIGN KEY (id)
	REFERENCES person (id);

ALTER TABLE service_person ADD CONSTRAINT service_person_reserve_fk FOREIGN KEY (reserve_id)
	REFERENCES person (id);

ALTER TABLE service ADD CONSTRAINT service_type_fk FOREIGN KEY (service_type)
	REFERENCES service_type (id);

