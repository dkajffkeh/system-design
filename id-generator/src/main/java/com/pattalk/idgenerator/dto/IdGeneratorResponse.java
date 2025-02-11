package com.pattalk.idgenerator.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class IdGeneratorResponse {

    private final Long id;
    private final LocalDateTime generatedTime;
    private final byte serverId;
    private final int sequence;

}
