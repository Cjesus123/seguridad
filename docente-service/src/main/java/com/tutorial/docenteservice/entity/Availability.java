package com.tutorial.docenteservice.entity;

import com.tutorial.docenteservice.enums.DayWeek;
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
    private String startTime;
    private String endTime;

    public Availability() {}

    public Availability(Integer id,DayWeek day, String startTime, String endTime) {
            validator(day,startTime,endTime);
            this.id = id;
            this.day = day;
            this.startTime = startTime;
            this.endTime = endTime;
    }

    private void validator(DayWeek day, String startTime, String endTime) {
        if(day == null)
            throw new RuntimeException("El dia no es valido");
        if(startTime == null || endTime == null){
            throw new RuntimeException("Horarios no validos");
        }
    }

}
