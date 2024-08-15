package com.t82.push.dto.request;


import com.t82.push.entity.Event;

import java.sql.Timestamp;

public record EventRequestDto (Long eventId, Timestamp eventStartTime){
    public static Event toEntity(EventRequestDto req) {
        return Event.builder()
                .eventId(req.eventId)
                .eventStartTime(req.eventStartTime)
                .build();
    }
}
