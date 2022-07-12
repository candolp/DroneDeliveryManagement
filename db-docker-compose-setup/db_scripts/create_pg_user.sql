CREATE ROLE dronetestuser WITH
    LOGIN
    SUPERUSER
    INHERIT
    CREATEDB
    CREATEROLE
    REPLICATION
    ENCRYPTED PASSWORD 'droneTestPass';

CREATE DATABASE dronesmanagement WITH OWNER dronetestuser;
\q
