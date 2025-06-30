package com.pattalk.idgenerator.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping
@RestController
public class VersionCheckerController {

    @GetMapping("/versions")
    public Mono<String> versionChecker() {
        return Mono.just("1.0.1");
    }
}
