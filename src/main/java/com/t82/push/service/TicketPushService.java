package com.t82.push.service;

import com.t82.push.dto.request.DeviceRequestDto;
import com.t82.push.dto.request.EventRequestDto;
import com.t82.push.dto.KafkaStatus;
import com.t82.push.repository.DeviceRepository;
import com.t82.push.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketPushService {

    private final EventRepository eventRepository;
    private final DeviceRepository deviceRepository;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void ticketPush() {

    }

    @KafkaListener(topics = "pushEventTopic", groupId = "pushEvent-group")
    public void saveEvent(EventRequestDto req){
        log.error("come event {}",req.toString());
        eventRepository.save(EventRequestDto.toEntity(req));
    }

    @KafkaListener(topics = "deviceTopic", groupId = "pushDevice-group")
    public void saveDevice(KafkaStatus<DeviceRequestDto> kafkaStatus){
        switch (kafkaStatus.status()){
            case "deviceToken"->{
                deviceRepository.save(DeviceRequestDto.toEntity(kafkaStatus.data()));
            }
        }
    }
}
