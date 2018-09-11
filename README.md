[![Casa Fácil Imóveis Logo](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/imagens/logo-photoshop-preto.png?raw=true)](https://github.com/WenderGalan/casa-facil-imoveis)

[![Build Status](https://travis-ci.org/WenderGalan/casa-facil-imoveis.svg?branch=master)](https://travis-ci.org/WenderGalan/casa-facil-imoveis) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/8c042476b08747baa4d6ece7b16f0708)](https://www.codacy.com/project/WenderGalan/casa-facil-imoveis/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=WenderGalan/casa-facil-imoveis&amp;utm_campaign=Badge_Grade_Dashboard) [![Front-end Developer](https://img.shields.io/badge/Front--end%20developer-Matheus%20Pimentel-brightgreen.svg)](https://github.com/MatheusPimentel) [![Front-end Developer](https://img.shields.io/badge/Back--end%20developer-Wender%20Galan-brightgreen.svg)](https://github.com/WenderGalan)

O projeto consiste em construir uma aplicação de compra/venda de imóveis parecido com alguns sites já existentes, a aplicação back-end não foi feita autenticação via Token, apenas utilizando o spring security para poder criptografar as senhas, quem controla a sessão do cliente é o front-end com o Vuex.

Para a instalação do spring boot é necessário fazer o download das dependências utilizadas e em seguida estartar a aplicação. Os comandos para poder rodar a aplicação front-end são:

```
- npm install
- npm run serve
```

## Documentação:
 - #### Documento de Visão e Escopo: [Sistema de venda e aluguel de imóveis](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Documento%20de%20Vis%C3%A3o%20e%20Escopo.pdf)
 - #### Sprint 1: [Anunciar Imóveis](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Sprint%201%20-%20Anunciar%20im%C3%B3veis.pdf)
 - #### Sprint 2: [Gerenciar anúncio e anunciante](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Sprint%202%20-%20Gerenciar%20an%C3%BAncio%20e%20anunciante.pdf)
 - #### Sprint 3: [Visualizar anúncio](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Sprint%203%20-%20Visualizar%20an%C3%BAncio.pdf)

## Tecnologias Utilizadas:

 - ### Back-end: [Spring boot](https://spring.io/projects/spring-boot) (dependências):
    - JPA Data: Gerenciamento das entidades da aplicação
    - Spring Security: Segurança dos web-services (Foi usado apenas para criptografia de senhas)
    - Spring Email: Usado para o envio de e-mails dentro da aplicação
    - Spring Web
    - Spring DevTools
    - [FlyWay](https://flywaydb.org/): Faz o gerenciamento de versões do banco de dados.
    - Postgres
    - Spring test (Teste de unidade)
    - [Swagger](https://swagger.io/): Faz o gerenciamento de todas as requisões (controllers) de dentro da aplicação facilitando a visualização das mesmas.
    
- ### Front-end: [Vue JS](https://vuejs.org/) (dependências):
     - [Bootstrap-Vue](https://bootstrap-vue.js.org/docs/): Usado para auxiliar na criação de componentes responsivos.
     - [Axios](https://github.com/axios/axios): Faz as requisições http com o back-end.
     - [Vue-Router](https://router.vuejs.org/): Ferramenta do Vue usada para fazer as rotas de usuário.
     - [ESLint](https://eslint.org/): Ferramenta para manter os padrões de código do JavaScript.
     - [Vuex](https://vuex.vuejs.org/): Ferramenta do Vue para fazer sessão e armazenar informações do usuário 
    
- ### Database: [Postgres](https://www.postgresql.org/)
    - Observações: A aplicação está com o banco de dados em deploy no Heroku, a principio da para deixar lá para poder utilizar a API sem necessidade de banco local, mas devido não ter custos o serviço é um pouco lento.
    
- ### Integração contínua: [Travis CI](https://travis-ci.org/)
     - O Travis CI é um serviço web de Integração Contínua na nuvem integrado com o GitHub. Ele é gratuito para repositórios públicos e pago para repositórios privados.
- ### Revisor de código: [Codacy](https://www.codacy.com/)
     - Codacy é um revisor de código automático que é feito em nuvem e integrado ao Github, quando é commitado algo automaticamente o mesmo já é chamado para poder fazer a revisão.

- ### Links para teste da aplicação:

     ### Back-end: [![Deploy](https://www.herokucdn.com/deploy/button.png)](https://casa-facil-imoveis.herokuapp.com/swagger-ui.html)
     ### Front-end: [![Deploy](https://www.herokucdn.com/deploy/button.png)](https://casa-facil-imoveis.herokuapp.com/)

- ### License:
```
Copyright 2018 Wender Galan - Matheus Pimentel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
