--PRIMEIRA VERSÃO DO BANCO DE DADOS
--TABLE USUARIOS--
CREATE TABLE public.usuarios
(
    id integer NOT NULL UNIQUE,
    nome text NOT NULL,
    email text NOT NULL,
    numero text NOT NULL,
    senha text NOT NULL,
    tipo_usuario integer NOT NULL,
    url_imagem text,
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

    -- TABLE ENDERECO --
CREATE TABLE public.endereco
(
    id integer NOT NULL,
    endereco text NOT NULL,
    complemento text NOT NULL,
    bairro text NOT NULL,
    numero integer,
    estado text NOT NULL,
    cidade text NOT NULL,
    cep text NOT NULL,
    latitude double precision,
    longitude double precision,
    id_anuncio integer NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.endereco
    OWNER to zxebwestoynydb;

CREATE SEQUENCE public.endereco_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

ALTER SEQUENCE public.endereco_id_seq
    OWNER TO zxebwestoynydb;


-- TABLE ANUNCIO --
CREATE TABLE public.anuncio
(
  id integer NOT NULL,
  titulo text NOT NULL,
  descricao text NOT NULL,
  valor double precision NOT NULL,
  tipo_imovel integer NOT NULL,
  id_anunciante integer NOT NULL,
  id_endereco integer NOT NULL,
  PRIMARY KEY(id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.anuncio
    OWNER to zxebwestoynydb;

CREATE SEQUENCE public.anuncio_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1;

ALTER SEQUENCE public.anuncio_id_seq
    OWNER TO zxebwestoynydb;

--FOREIGN KEYS--
    ALTER TABLE public.endereco ADD CONSTRAINT fk_endereco FOREIGN KEY (id_anuncio)
    REFERENCES public.anuncio (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE public.anuncio ADD CONSTRAINT fk_anunciante FOREIGN KEY (id_anunciante)
    REFERENCES public.anuncio (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE public.anuncio ADD CONSTRAINT fk_endereco FOREIGN KEY (id_endereco)
    REFERENCES public.endereco (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

    ALTER TABLE public.imagens ADD CONSTRAINT fk_anuncio FOREIGN KEY (id_anuncio)
    REFERENCES public.anuncio (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;





