package com.conlean.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);


    @GetMapping("/")
    public String wellcome() {
        logger.info("Hi !");
        return "Hi friend !";
    }
}
