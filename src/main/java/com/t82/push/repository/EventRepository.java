package com.t82.push.repository;


import com.t82.push.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * 입력값 안에 시작하는 공연 가져오기
     */
    @Query("select e from Event e where e.eventStartTime >= :startTime and e.eventStartTime <= :endTime")
    List<Event> findEventsWithinTimeRange(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);



}
