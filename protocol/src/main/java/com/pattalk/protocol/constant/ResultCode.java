package com.pattalk.protocol.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResultCode {

    /** API 요청에 대한 Validation 실패 */
    BAD_REQUEST("4000"),
    ;

    private final String resultCode;

    public String getResultCode() {
        return resultCode;
    }
}
