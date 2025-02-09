package com.pattalk.idgenerator.presentation;

import com.pattalk.protocol.payload.ApiResult;
import com.pattalk.protocol.payload.response.IdResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/v1/id")
public class IdController {

    @GetMapping
    public ApiResult<IdResponseDto> generateId() {
        return null;
    }
}
