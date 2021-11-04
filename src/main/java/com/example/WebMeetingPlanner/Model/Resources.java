package com.example.WebMeetingPlanner.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="resources")
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Resource_id;

    @Column(nullable = false, length = 20)
    private String Resource;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "room_id")

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resources")


    private List<TracomRooms> room = new ArrayList<>();

    //    private List<Resources> resources = new ArrayList<Resources>();


    public Resources() {
    }


    public Resources(Long resource_id, String resource) {
        Resource_id = resource_id;
        Resource = resource;
    }

    public List<TracomRooms> getRoom() {
        return room;
    }

    public void setRoom(List<TracomRooms> room) {
        this.room = room;
    }

    public String getResource() {
        return Resource;
    }

    public void setResource(String resource) {
        Resource = resource;
    }

    public Long getResource_id() {
        return Resource_id;
    }

    public void setResource_id(Long resource_id) {
        Resource_id = resource_id;
    }
}
