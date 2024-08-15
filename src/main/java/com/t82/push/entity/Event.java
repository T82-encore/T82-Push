package com.t82.push.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "EVENTS")
public class Event {
    @Id
    @Column(name = "EVNET_ID")
    private Long eventId;
    @Column(name = "EVENT_START_TIME")
    private Timestamp evnetStartTime;
}