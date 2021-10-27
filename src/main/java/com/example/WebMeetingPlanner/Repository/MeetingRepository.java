package com.example.WebMeetingPlanner.Repository;

import com.example.WebMeetingPlanner.Model.meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MeetingRepository extends JpaRepository<meeting, Integer>
{
    @Query("SELECT e FROM meeting e WHERE e.meeting_id=?1")
     meeting findByMeeting_id(Integer meeting_id);



}
