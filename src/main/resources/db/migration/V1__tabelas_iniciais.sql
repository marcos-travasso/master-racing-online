CREATE TABLE pilots
(
    id serial constraint pilots_fk primary key,
    name varchar
);

CREATE TABLE circuits
(
    id serial constraint circuits_pk primary key,
    name varchar
);

CREATE TABLE races
(
    id serial constraint races_pk primary key,
    category varchar,
    description varchar,
    laps int,
    start_time timestamp,
    circuit_id integer constraint races_circuit_id_fk
        references circuits
);

CREATE TABLE participation
(
    id serial constraint participation_pk primary key,
    pilot_id integer constraint participation_pilot_id_fk
        references pilots,
    race_id integer constraint participation_race_id_fk
        references races
);

CREATE TABLE laps
(
    id serial constraint laps_pk primary key,
    actual_time timestamp,
    time_elapsed int,
    participation_id integer constraint laps_participation_id_fk
        references participation
);