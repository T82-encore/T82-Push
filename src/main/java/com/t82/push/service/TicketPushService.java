package com.t82.push.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.t82.push.api.ApiFeign;
import com.t82.push.dto.request.DeviceRequestDto;
import com.t82.push.dto.request.EventNotificationRequestDto;
import com.t82.push.dto.request.EventRequestDto;
import com.t82.push.dto.KafkaStatus;
import com.t82.push.dto.response.UserResponseDto;
import com.t82.push.entity.Device;
import com.t82.push.entity.Event;
import com.t82.push.repository.DeviceRepository;
import com.t82.push.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketPushService {

    private final EventRepository eventRepository;
    private final DeviceRepository deviceRepository;
    private final ApiFeign apiFeign;
    private final FirebaseMessaging firebaseMessaging;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void ticketPush() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Timestamp startTime = new Timestamp(currentTime.getTime() - 60 * 1000);
        Timestamp endTime = new Timestamp(currentTime.getTime() + 30 * 60 * 1000); // 30분 후

        List<Event> events = eventRepository.findEventsWithinTimeRange(startTime, endTime);
        // feign으로 해당하는 유저 찾아오기
        events.forEach(event -> {
            List<UserResponseDto> users = apiFeign.getUsers(event.getEventId());
            users.forEach(user -> {
                log.warn("가져온 userId {}",user.userId());
                Optional<Device> device = deviceRepository.findByUserId(user.userId());
                if(device.isEmpty()) return;
//                deviceToken으로 push 보냄
                Message message = Message.builder()
                        .setToken(device.get().getDeviceToken())
                        .setNotification(new EventNotificationRequestDto("공연 시작시간이 얼마 남지 않았습니다","서둘러주세요!").toNotification())
                        .build();
                try {
                    log.info("발송! {}",user.userId());
                    firebaseMessaging.send(message);
                } catch (FirebaseMessagingException e) {
                    throw new IllegalArgumentException();
                }
            });
        });

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
