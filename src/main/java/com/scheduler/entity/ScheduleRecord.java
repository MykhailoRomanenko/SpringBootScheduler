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
    @NotNull(message = "id cannot be null")
    private UUID id;

    @Column(name = "room", length = 250)
    @NotBlank(message = "Room number cannot be empty")
    @NotEmpty
    private String room;

    @Column(name = "timeslot")
    @Convert(converter = TimeslotConverter.class)
    private Timeslot timeslot;

    @Column(name = "day")
    @Min(1)
    @Max(7)
    private Integer day;

    @Column(name = "weeks", length = 250)
    @NotBlank(message = "Week name cannot be empty")
    @NotEmpty
    private String weeks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    @NotNull(message = "id cannot be null")
    private Class scheduleClass;
}
