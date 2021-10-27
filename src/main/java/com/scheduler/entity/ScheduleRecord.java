package com.scheduler.entity;

import com.scheduler.converter.TimeslotConverter;
import com.scheduler.model.Timeslot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.UUID;

@Entity
@Table(name = "schedule_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRecord {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "room", length = 250)
    private String room;

    @Column(name = "timeslot")
    @FutureOrPresent
    @Convert(converter = TimeslotConverter.class)
    private Timeslot timeslot;

    @Column(name = "day")
    private Integer day;

    @Column(name = "weeks", length = 250)
    private String weeks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class scheduleClass;
}
