package com.example.WebMeetingPlanner.Repository;

import com.example.WebMeetingPlanner.Model.TracomRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomsRepository  extends JpaRepository<TracomRooms, Integer>

{
    @Query("SELECT f FROM TracomRooms f WHERE f.room_id=?1")
    TracomRooms findByRoom_id(Integer Room_id);
}
