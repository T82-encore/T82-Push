package com.t82.push.api;

import com.t82.push.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiFeign {
    private final FeignTicket feignTicket;

    public List<UserResponseDto> getUsers(Long eventId) {
        try {
            return feignTicket.getUsers(eventId);
        } catch (Exception e) {
            // 원래의 예외 메시지를 로그에 남기기
            throw new IllegalArgumentException("Error occurred while fetching users: " + e.getMessage(), e);
        }
    }
}