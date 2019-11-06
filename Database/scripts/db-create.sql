-- Table: public.usuario

-- DROP TABLE public.usuario;

CREATE TABLE public.usuario
(
    id integer NOT NULL,
    email character varying(200) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    numero character varying(11) COLLATE pg_catalog."default",
    senha character varying(500) COLLATE pg_catalog."default" NOT NULL,
    tipo_usuario integer NOT NULL,
    url_imagem character varying(255) COLLATE pg_catalog."default",
    cpf character varying(255) COLLATE pg_catalog."default",
    cnpj character varying(255) COLLATE pg_catalog."default",
    creci character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to postgres;

-- Table: public.endereco

-- DROP TABLE public.endereco;

CREATE TABLE public.endereco
(
    id integer NOT NULL,
    bairro character varying(100) COLLATE pg_catalog."default" NOT NULL,
    cep character varying(100) COLLATE pg_catalog."default" NOT NULL,
    cidade character varying(100) COLLATE pg_catalog."default" NOT NULL,
    complemento character varying(100) COLLATE pg_catalog."default",
    endereco character varying(200) COLLATE pg_catalog."default" NOT NULL,
    estado character varying(2) COLLATE pg_catalog."default" NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    numero integer,
    CONSTRAINT endereco_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.endereco
    OWNER to postgres;

-- Table: public.crashlytics

-- DROP TABLE public.crashlytics;

CREATE TABLE public.crashlytics
(
    id integer NOT NULL,
    class_error character varying(500) COLLATE pg_catalog."default",
    class_name character varying(100) COLLATE pg_catalog."default",
    exception_stack character varying(30000) COLLATE pg_catalog."default",
    file_name character varying(100) COLLATE pg_catalog."default",
    language character varying(10) COLLATE pg_catalog."default",
    line_number character varying(10) COLLATE pg_catalog."default",
    method_name character varying(500) COLLATE pg_catalog."default",
    method_url character varying(10) COLLATE pg_catalog."default",
    url character varying(500) COLLATE pg_catalog."default",
    CONSTRAINT crashlytics_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.crashlytics
    OWNER to postgres;

    -- Table: public.header

-- DROP TABLE public.header;

CREATE TABLE public.header
(
    id integer NOT NULL,
    key character varying(500) COLLATE pg_catalog."default" NOT NULL,
    value character varying(500) COLLATE pg_catalog."default" NOT NULL,
    id_crashlytics integer NOT NULL,
    CONSTRAINT header_pkey PRIMARY KEY (id),
    CONSTRAINT fk_header_crashlytics FOREIGN KEY (id_crashlytics)
        REFERENCES public.crashlytics (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.header
    OWNER to postgres;

-- Table: public.anuncio

-- DROP TABLE public.anuncio;

CREATE TABLE public.anuncio
(
    id integer NOT NULL,
    descricao character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    tipo_imovel integer NOT NULL,
    tipo_negocio integer NOT NULL,
    titulo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    valor double precision NOT NULL,
    id_anunciante integer,
    id_endereco integer,
    CONSTRAINT anuncio_pkey PRIMARY KEY (id),
    CONSTRAINT fk_anuncio_anunciante FOREIGN KEY (id_anunciante)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_anuncio_endereco FOREIGN KEY (id_endereco)
        REFERENCES public.endereco (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.anuncio
    OWNER to postgres;

    -- Table: public.favorito

-- DROP TABLE public.favorito;

CREATE TABLE public.favorito
(
    id integer NOT NULL,
    id_anuncio integer,
    id_anunciante integer,
    CONSTRAINT favorito_pkey PRIMARY KEY (id),
    CONSTRAINT fkc60u995hkaqtn6y8mqxl532nw FOREIGN KEY (id_anunciante)
        REFERENCES public.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fks9n8oq9oc8nqi9rsfud5dpcjc FOREIGN KEY (id_anuncio)
        REFERENCES public.anuncio (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.favorito
    OWNER to postgres;

    -- Table: public.imagem

-- DROP TABLE public.imagem;

CREATE TABLE public.imagem
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    imagem_url character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id_anuncio integer,
    CONSTRAINT imagem_pkey PRIMARY KEY (id),
    CONSTRAINT fkpj66s2pdpdn3rg8myr2wud6ac FOREIGN KEY (id_anuncio)
        REFERENCES public.anuncio (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.imagem
    OWNER to postgres;