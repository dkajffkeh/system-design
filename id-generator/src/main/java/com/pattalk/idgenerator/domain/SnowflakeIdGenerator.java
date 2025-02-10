package com.pattalk.idgenerator.domain;

import com.pattalk.idgenerator.dto.IdGeneratorResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicLong;

public class SnowflakeIdGenerator {

    public static final long EPOCH = LocalDateTime
            .of(2024, 1, 1, 0, 0, 0)
            .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

    private static final long SERVER_ID_BITS = 5L;
    private static final long SEQUENCE_BITS = 12L;

    // 31
    private static final long MAX_WORKER_ID = (1L << SERVER_ID_BITS) - 1;
    private static final long SEQUENCE_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + SERVER_ID_BITS;

    private final long workerId;
    private final AtomicLong sequence = new AtomicLong(0);
    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator(long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("Worker ID must be between 0 and " + MAX_WORKER_ID);
        }
        this.workerId = workerId;
    }

    private synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Rejecting requests.");
        }

        if (timestamp == lastTimestamp) {
            // timeMills 당 4096 개의 아이디를 생성할 수 있음.
            long seq = (sequence.incrementAndGet()) & ((1L << SEQUENCE_BITS) - 1);
            if (seq == 0) {
                timestamp = waitNextMillis(timestamp);
            }
        } else {
            // 초기화
            sequence.set(0);
        }

        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (workerId << SEQUENCE_ID_SHIFT)
                | sequence.get();
    }

    private long waitNextMillis(long timestamp) {
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        System.out.println((4096) & ((1L << 12) - 1));
    }

    public IdGeneratorResponse parser() {
        final Long id = this.nextId();

        // 17 개 오른쪽으로 밈
        long timestamp = (id >> TIMESTAMP_SHIFT) + EPOCH;
        LocalDateTime generatedTime = LocalDateTime.ofInstant(
                java.time.Instant.ofEpochMilli(timestamp),
                ZoneId.systemDefault()
        );

        byte serverId = (byte) ((id >> SEQUENCE_ID_SHIFT) & 0x1F);

        int sequence = (int) (id & 0xFFF);

        return new IdGeneratorResponse(id, generatedTime, serverId, sequence);
    }
}
