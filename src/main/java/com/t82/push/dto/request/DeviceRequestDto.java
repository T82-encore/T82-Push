package com.t82.push.dto.request;


import com.t82.push.entity.Device;

public record DeviceRequestDto(String userId, String deviceToken){
    public static Device toEntity(DeviceRequestDto req) {
        return Device.builder()
                .deviceToken(req.deviceToken)
                .userId(req.userId)
                .build();
    }
}
