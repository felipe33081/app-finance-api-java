-- PostgreSQL database dump

-- Criar usuário
CREATE USER finance WITH PASSWORD 'finance';
ALTER USER finance WITH SUPERUSER;

-- Criar banco de dados
CREATE DATABASE finance WITH OWNER = finance TABLESPACE = pg_default TEMPLATE = template0
    ENCODING = 'UTF8' LC_COLLATE = 'pt_BR.UTF-8' LC_CTYPE = 'pt_BR.UTF-8' CONNECTION LIMIT = -1;

-- Criar schema e extensão no banco 'finance'
CREATE SCHEMA finance AUTHORIZATION finance;

GRANT ALL ON SCHEMA finance TO finance WITH GRANT OPTION;

SET search_path TO finance;

ALTER DATABASE finance SET search_path TO finance;

CREATE EXTENSION unaccent SCHEMA finance;
