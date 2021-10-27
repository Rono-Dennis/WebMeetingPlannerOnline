package com.example.WebMeetingPlanner.Model;

import javax.persistence.*;

@Entity
@Table(name="tracomRooms")
public class TracomRooms
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long room_id;

    @Column(nullable = false, length = 20)
    private String tracomrooms;
    @Column(nullable = false, length = 20)
    private String pergamonrooms;

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public String getTracomrooms() {
        return tracomrooms;
    }

    public void setTracomrooms(String tracomrooms) {
        this.tracomrooms = tracomrooms;
    }

    public String getPergamonrooms() {
        return pergamonrooms;
    }

    public void setPergamonrooms(String pergamonrooms) {
        this.pergamonrooms = pergamonrooms;
    }

    @Override
    public String toString() {
        return "TracomRooms{" +
                "room_id=" + room_id +
                ", tracomrooms='" + tracomrooms + '\'' +
                ", pergamonrooms='" + pergamonrooms + '\'' +
                '}';
    }
}
