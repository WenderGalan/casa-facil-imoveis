# Casa Fácil Imóveis 

[![Build Status](https://travis-ci.org/WenderGalan/casa-facil-imoveis.svg?branch=master)](https://travis-ci.org/WenderGalan/casa-facil-imoveis) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![GitHub contributors](https://img.shields.io/github/contributors/Naereen/StrapDown.js.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/contributors/) 

O projeto consiste em construir uma aplicação de compra/venda de imóveis parecido com alguns sites já existentes. 

O projeto está divido da seguinte maneira, o front-end foi desenvolvido pelo [Matheus Pimentel](https://github.com/MatheusPimentel) e o back-end por [Wender Galan](https://github.com/WenderGalan). 

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

- ### Links para teste da aplicação:

     ### Back-end: [![Deploy](https://www.herokucdn.com/deploy/button.png)](https://casa-facil-imoveis.herokuapp.com/swagger-ui.html)
     ### Front-end: [![Deploy](https://www.herokucdn.com/deploy/button.png)](https://casa-facil-imoveis.herokuapp.com/)
