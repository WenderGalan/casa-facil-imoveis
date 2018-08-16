package com.casafacilimoveis.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Controller de requisições de anúncios")
@RestController
@CrossOrigin
@RequestMapping("/anuncios")
public class AnuncioController {
}
