package com.pattalk.idgenerator.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IdGeneratorResponse {

    private final Long id;
    private final LocalDateTime generatedTime;
    private final byte serverId;
    private final int sequence;

}
