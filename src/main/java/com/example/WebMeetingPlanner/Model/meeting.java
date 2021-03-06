package com.example.WebMeetingPlanner.Model;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="meeting")
public class meeting
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long meeting_id;
    @Column(nullable = false,length = 45)
    private String meeting_start_time;
    @Column(nullable = false,length = 45)
    private String meeting_start_date;
    @Column(nullable = false,length = 64)
    private String meeting_end_time;
    @Column(nullable = false, length = 20)
    private String meeting_end_date;
    @Column(nullable = false, length = 20)
    private String eventPattern;
    @Column(nullable = false, length = 20)
    private String capacity;
    @Column(nullable = false, length = 20)
    private String topic;

    @Column(nullable = false, length = 30)
    private String organisationName;

    public meeting() {
        this.meeting_id = meeting_id;
        this.meeting_start_time = meeting_start_time;
        this.meeting_start_date = meeting_start_date;
        this.meeting_end_time = meeting_end_time;
        this.meeting_end_date = meeting_end_date;
        this.eventPattern = eventPattern;
        this.capacity = capacity;
        this.topic = topic;
        this.organisationName = organisationName;
    }

    public long getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(long meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getMeeting_start_time() {
        return meeting_start_time;
    }

    public void setMeeting_start_time(String meeting_start_time) {
        this.meeting_start_time = meeting_start_time;
    }

    public String getMeeting_start_date() {
        return meeting_start_date;
    }

    public void setMeeting_start_date(String meeting_start_date) {
        this.meeting_start_date = meeting_start_date;
    }

    public String getMeeting_end_time() {
        return meeting_end_time;
    }

    public void setMeeting_end_time(String meeting_end_time) {
        this.meeting_end_time = meeting_end_time;
    }

    public String getMeeting_end_date() {
        return meeting_end_date;
    }

    public void setMeeting_end_date(String meeting_end_date) {
        this.meeting_end_date = meeting_end_date;
    }

    public String getEventPattern() {
        return eventPattern;
    }

    public void setEventPattern(String eventPattern) {
        this.eventPattern = eventPattern;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    @Override
    public String toString() {
        return "meeting{" +
                "meeting_id=" + meeting_id +
                ", meeting_start_time='" + meeting_start_time + '\'' +
                ", meeting_start_date='" + meeting_start_date + '\'' +
                ", meeting_end_time='" + meeting_end_time + '\'' +
                ", meeting_end_date='" + meeting_end_date + '\'' +
                ", eventPattern='" + eventPattern + '\'' +
                ", capacity='" + capacity + '\'' +
                ", topic='" + topic + '\'' +
                ", organisationName='" + organisationName + '\'' +
                '}';
    }
}

