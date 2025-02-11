package com.pattalk.idgenerator.presentation;

import com.pattalk.idgenerator.business.IdGenerateService;
import com.pattalk.idgenerator.dto.IdGeneratorResponse;
import com.pattalk.protocol.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/id")
public class IdController {

    private final IdGenerateService idGenerateService;

    @GetMapping
    public Mono<ApiResult<IdGeneratorResponse>> generateId() {
        return Mono.just(ApiResult.succeed(this.idGenerateService.generateId()));
    }
}
