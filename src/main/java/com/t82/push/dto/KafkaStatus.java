package com.t82.push.dto;

public record KafkaStatus<T>(
        T data, String status
) {
}
