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
    url_imagem integer NOT NULL,
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

--TABLE IMAGEM--
CREATE TABLE public.imagens
(
    id integer NOT NULL,
    imagem_url text NOT NULL,
    id_anuncio integer NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.imagens
    OWNER to zxebwestoynydb;

-- TABLE ANUNCIO --



