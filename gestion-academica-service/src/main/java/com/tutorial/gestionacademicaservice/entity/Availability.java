package com.tutorial.gestionacademicaservice.entity;

import com.tutorial.gestionacademicaservice.enums.DayWeek;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private DayWeek day;

    private String startTime; // Formato "HH:mm"
    private String endTime;    // Formato "HH:mm"

    public Availability() {}

    public Availability(DayWeek day, String startTime, String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
