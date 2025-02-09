package com.pattalk.protocol.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult<T> {

    private final String code;
    private final String message;

    private final T data;

    /**
     *
     * @param code 응답 코드
     * @param message 응답 메시지
     * @param data 응답 데이터
     */
    @Builder
    protected ApiResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private static <T> ApiResult<T> succeed(String code, String message, T data) {
        return ApiResult.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResult<T> failed(String code, String message) {
        return ApiResult.<T>builder()
                .code(code)
                .message(message)
                .data(null)
                .build();
    }
}
