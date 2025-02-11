package com.pattalk.idgenerator.business;

import com.pattalk.idgenerator.domain.SnowflakeIdGenerator;
import com.pattalk.idgenerator.dto.IdGeneratorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdGenerateService {

    private final SnowflakeIdGenerator snowflakeIdGenerator;

    public IdGeneratorResponse generateId() {
        return snowflakeIdGenerator.parser();
    }
}
