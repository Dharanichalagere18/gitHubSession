package com.vivanet.talentzone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidatesController {

    @GetMapping("/candidates")
    public String hello() {
        return "Hello World";
    }

}