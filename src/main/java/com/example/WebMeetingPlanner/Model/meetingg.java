package com.example.WebMeetingPlanner.Model;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="meetingg")
public class meetingg
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long meeting_id;


    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private String meeting_start_time;


    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private String meeting_start_date;


    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private String meeting_end_time;


    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private String meeting_end_date;

    private String eventPattern;

    private String capacity;

    private String topic;

    private String organisationName;

}

