package com.pattalk.idgenerator.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthChecker {

    @GetMapping
    public String healthCheck() {
        return "pong";
    }

}
