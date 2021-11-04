package com.example.WebMeetingPlanner.Model;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.WebMeetingPlanner.Validation.DateOrderCheck;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="meeting")
@DateOrderCheck(startDateField = "startDate", endDateField = "endDate", message = "{end.date.before.start}")
public class Dates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long meeting_id;
    private static final String AU_DATE_FORMAT = "dd/MM/yyyy";

    @NotNull(message = "{start.date.mandatory}")
    @DateTimeFormat(pattern = AU_DATE_FORMAT)
    private Date startDate;

    @NotNull(message = "{end.date.mandatory}")
    @DateTimeFormat(pattern = AU_DATE_FORMAT)
    private Date endDate;

    @Column(nullable = false, length = 20)
    private String eventPattern;
    @Column(nullable = false, length = 20)
    private String capacity;
    @Column(nullable = false, length = 20)
    private String topic;

    @Column(nullable = false, length = 30)
    private String organisationName;

    public Dates() {
    }

    public Dates(long meeting_id, Date startDate, Date endDate, String eventPattern, String capacity, String topic, String organisationName) {
        this.meeting_id = meeting_id;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
