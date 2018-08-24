package com.casafacilimoveis.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: SwaggerConfiguration.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Confiura o swagger para poder ter a visualização em HTML
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                //Passar o package de onde estão os controller - irá fazer a busca dos controller
                .apis(RequestHandlerSelectors.basePackage("com.casafacilimoveis"))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        //Preenche as informações de contato do dev
        Contact contact = new Contact("Wender Galan", "http://wendergalan.com", "contato@wendergalan.com");

        return new ApiInfoBuilder().title("Casa Fácil Imóveis API")
                .description("Documentação da API de acesso aos endpoints da aplicação Casa Fácil Imóveis.")
                .contact(contact)
                .license("Apache License Version 2.0")
                .licenseUrl("https://opensource.org/licenses/Apache-2.0")
                .version("1.0-SNAPSHOT")
                .build();
    }

}
