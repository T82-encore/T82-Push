package com.t82.push.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "DEVICES")
public class Device {
    @Id
    @Column(name = "DEVICE_TOKEN")
    private String deviceToken;
    @Column(name = "USER_ID")
    private String userId;
}