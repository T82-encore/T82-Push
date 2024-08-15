package com.t82.push.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.t82.push.dto.request.NotificationRequestDto;
import com.t82.push.service.FcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class FcmController {
    private final FcmService fcmService;

    @PostMapping("/send-notification")
    public void sendNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        try {
            fcmService.sendNotification(notificationRequestDto);
        } catch (FirebaseMessagingException e) {
            throw new IllegalArgumentException();
        }
    }

}