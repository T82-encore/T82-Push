package com.t82.push.dto.request;


import com.t82.push.entity.Event;

import java.sql.Timestamp;

public record PushEventDto(Long eventId, Timestamp eventStartTime){
    public static Event toEntity(PushEventDto req) {

        return Event.builder()
                .eventId(req.eventId)
                .eventStartTime(Timestamp.valueOf(req.eventStartTime.toLocalDateTime().minusHours(9)))
                .build();
    }
}
