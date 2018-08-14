--PRIMEIRA VERSÃO DO BANCO DE DADOS
--TABLE USUARIOS--
CREATE TABLE public.usuarios
(
    id integer NOT NULL,
    nome text NOT NULL,
    email text NOT NULL,
    numero text NOT NULL,
    senha text NOT NULL,
    tipo_usuario integer NOT NULL,
    PRIMARY KEY (id, email)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.usuarios
    OWNER to zxebwestoynydb;

CREATE SEQUENCE public.usuarios_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

ALTER SEQUENCE public.usuarios_id_seq
    OWNER TO zxebwestoynydb;