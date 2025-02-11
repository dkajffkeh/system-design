package com.pattalk.protocol.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpStatus {

    OK("200", "OK")
    ;

    private final String status;
    private final String message;


}
