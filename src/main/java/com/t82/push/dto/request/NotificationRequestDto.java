package com.t82.push.dto.request;

import lombok.Builder;


import com.google.firebase.messaging.Notification;
import jakarta.validation.constraints.NotBlank;

public record NotificationRequestDto(@NotBlank String deviceToken,
                                  String title,
                                  String body) {

    @Builder
    public NotificationRequestDto {}

    public Notification toNotification() {
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
    }
}