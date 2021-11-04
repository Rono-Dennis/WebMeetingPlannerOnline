package com.example.WebMeetingPlanner.Model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="BoardRooms")
public class TracomRooms
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long room_id;

    @Column(nullable = false, length = 20)
    private String rooms;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="resource_id")
    private Resources resources;

//    private List<Resources> resources = new ArrayList<Resources>();

    public TracomRooms() {
    }

    public TracomRooms(long room_id, String rooms) {
        this.room_id = room_id;
        this.rooms = rooms;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }
}
