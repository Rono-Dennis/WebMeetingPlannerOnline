package com.example.WebMeetingPlanner.Repository;

import com.example.WebMeetingPlanner.Model.Resources;
import com.example.WebMeetingPlanner.Model.TracomRooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResourcesRepository extends JpaRepository<Resources, Long>

{
    @Query("SELECT g FROM Resources g WHERE g.Resource_id=?1")
    Resources findByResource_id(long Resource_id);
}
