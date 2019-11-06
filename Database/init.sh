#!/bin/bash
set -e
echo 'Começando a execução da inicialização do banco de dados'
psql -v ON_ERROR_STOP=1 --username "${POSTGRES_USER}" --dbname "${POSTGRES_DB}" -f /scripts/db-create.sql
psql -v ON_ERROR_STOP=1 --username "${POSTGRES_USER}" --dbname "${POSTGRES_DB}" -f /scripts/usuarios.sql
psql -v ON_ERROR_STOP=1 --username "${POSTGRES_USER}" --dbname "${POSTGRES_DB}" -f /scripts/enderecos.sql
psql -v ON_ERROR_STOP=1 --username "${POSTGRES_USER}" --dbname "${POSTGRES_DB}" -f /scripts/anuncios.sql
psql -v ON_ERROR_STOP=1 --username "${POSTGRES_USER}" --dbname "${POSTGRES_DB}" -f /scripts/imagens.sql

#psql -v ON_ERROR_STOP=1 --username "${POSTGRES_USER}" --dbname "${POSTGRES_DB}" -f /docker-entrypoint-initdb.d/anuncios.sql

#echo 'Começando a execução da inicialização do banco de dados'
## Cria o banco de dados
#sqlplus "${POSTGRES_USER}"/"${POSTGRES_PASSWORD}" /init.sql
## Popula o banco de dados
#sqlplus "${POSTGRES_USER}"/"${POSTGRES_PASSWORD}" /populate/imagens.sql
#sqlplus "${POSTGRES_USER}"/"${POSTGRES_PASSWORD}" /populate/enderecos.sql
#sqlplus "${POSTGRES_USER}"/"${POSTGRES_PASSWORD}" /populate/anuncios.sql