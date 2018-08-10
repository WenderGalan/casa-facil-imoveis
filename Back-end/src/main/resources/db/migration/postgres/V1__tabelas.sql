--PRIMEIRA VERSÃO DO BANCO DE DADOS
CREATE TABLE public.usuarios (
  id INTEGER NOT NULL,
  nome TEXT NOT NULL,
  login VARCHAR(500) NOT NULL,
  senha VARCHAR(1000),
  PRIMARY KEY(id)
)
WITH (oids = false);

INSERT INTO public.usuarios (id, nome, login, senha) VALUES (1, 'Jose Teste', 'josesinho', 'senha');