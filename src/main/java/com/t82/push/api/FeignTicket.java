package com.t82.push.api;

import com.t82.push.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "ticket-service", url = "${ticket.path}")
public interface FeignTicket {
    @GetMapping("/tickets/{eventId}")
    List<UserResponseDto> getUsers(@PathVariable("eventId") Long eventId);

}