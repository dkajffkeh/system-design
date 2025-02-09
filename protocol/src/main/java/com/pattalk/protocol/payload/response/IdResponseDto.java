package com.pattalk.protocol.payload.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IdResponseDto {

    private final Long id;
    private final LocalDateTime localdateTime;
    private final Long dataCenterId;
    private final Long serverId;
    private final Long sequence;

}
