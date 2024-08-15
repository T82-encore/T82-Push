package com.t82.push.dto.request;

import com.google.firebase.messaging.Notification;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record EventNotificationRequestDto(String title, String body) {

    @Builder
    public EventNotificationRequestDto {}

    public Notification toNotification() {
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
    }
}