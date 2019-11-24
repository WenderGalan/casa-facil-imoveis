[![Casa Fácil Imóveis Logo](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/imagens/logo-photoshop-preto.png?raw=true)](https://github.com/WenderGalan/casa-facil-imoveis)

[![Build Status](https://travis-ci.org/WenderGalan/casa-facil-imoveis.svg?branch=master)](https://travis-ci.org/WenderGalan/casa-facil-imoveis) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=com.lapots.breed.judge:judge-rule-engine&metric=alert_status)](https://sonarcloud.io/dashboard?id=WenderGalan_casa-facil-imoveis)  [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/8c042476b08747baa4d6ece7b16f0708)](https://www.codacy.com/project/WenderGalan/casa-facil-imoveis/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=WenderGalan/casa-facil-imoveis&amp;utm_campaign=Badge_Grade_Dashboard) [![Front-end Developer](https://img.shields.io/badge/Front--end%20developer-Matheus%20Pimentel-brightgreen.svg)](https://github.com/MatheusPimentel) [![Front-end Developer](https://img.shields.io/badge/Back--end%20developer-Wender%20Galan-brightgreen.svg)](https://github.com/WenderGalan)

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
  - #### Sprint 4: [Busca e Paginação de imóveis](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Sprint%204%20-%20Busca%20e%20Pagina%C3%A7%C3%A3o%20de%20im%C3%B3veis.pdf)
  - #### Sprint 5: [Autocomplete na busca de imóveis](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Sprint%205%20-%20Autocomplete%20na%20busca%20de%20im%C3%B3veis.pdf)
 - #### Sprint 6: [Relatórios de venda e aluguel](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Sprint%206%20-%20Relat%C3%B3rios%20de%20Venda%20e%20Aluguel.pdf)
 - #### Sprint 7: [Relatórios de venda e aluguel - Multiformatos](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Sprint%207%20-%20Relat%C3%B3rios%20de%20Venda%20e%20Aluguel%20-%20Multiformatos.pdf) 
   - ##### Modelos de relatórios disponíveis: [Venda e Aluguel](https://github.com/WenderGalan/casa-facil-imoveis/tree/master/Relat%C3%B3rios)
  - #### Sprint 8: [Relatórios de Venda e Aluguel - Múltiplos templates](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Sprint%208%20-%20Relat%C3%B3rios%20de%20Venda%20e%20Aluguel%20-%20M%C3%BAltiplos%20templates.pdf)
  - #### Sprint 9: [Cadastro e gerência de cliente](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Sprint%209%20-%20Cadastro%20e%20ger%C3%AAncia%20de%20cliente.pdf)
  - #### Sprint 10: [Engenharia reversa e análise estática](https://github.com/WenderGalan/casa-facil-imoveis/blob/master/Documenta%C3%A7%C3%A3o/Sprint%2010%20-%20Engenharia%20Reversa%20e%20An%C3%A1lise%20Est%C3%A1tica.pdf)
 
## Tecnologias Utilizadas:

 - ### Back-end: [Spring boot](https://spring.io/projects/spring-boot) (dependências):
    - JPA Data: Gerenciamento das entidades da aplicação
    - Spring Security: Segurança dos web-services (Foi usado apenas para criptografia de senhas)
    - Spring Email: Usado para o envio de e-mails dentro da aplicação
    - Spring Web
    - Spring Cache
    - Spring DevTools
    - Jasper Reports
    - Captura de exceções e armazenamento em banco
    - [FlyWay](https://flywaydb.org/): Faz o gerenciamento de versões do banco de dados.
    - Postgres
    - Spring test (Teste de unidade)
    - [Swagger](https://swagger.io/): Faz o gerenciamento de todas as requisões (controllers) de dentro da aplicação facilitando a visualização das mesmas.
    - [Lombok](https://projectlombok.org/): O Project Lombok é uma biblioteca java que se conecta automaticamente ao seu editor e cria ferramentas, apimentando o seu desenvolvimento java.
    
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
     
- ### [Docker](https://www.docker.com/):
   - [Docker Compose](https://docs.docker.com/compose/)

- ### Links para teste da aplicação:

     ### Back-end: [![Deploy](https://www.herokucdn.com/deploy/button.png)](https://casa-facil-imoveis.herokuapp.com/swagger-ui.html)
     ### Front-end: [![Deploy](https://www.herokucdn.com/deploy/button.png)](https://casa-facil-imoveis.herokuapp.com/)

- ### Como rodar o projeto?
  Assumindo que já tenha o Docker instalado na máquina, execute os procedimentos abaixo:

  Abra o console na pasta raiz da aplicação

  Execute o deploy, atualiza a imagem da aplicação e cria o banco Postgresql populado
  
  ```
  docker-compose up -d --build
  ```

  No seu browser acesse as urls: 
    - Front-end: http://localhost:5000
    - Back-end: http://localhost:8080/swagger-ui.html
    - Banco de dados: http://localhost:16543
       - Credenciais do mesmo está no common.env na raiz do projeto.

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
