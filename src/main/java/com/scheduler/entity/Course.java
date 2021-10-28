package com.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull(message = "id cannot be null")
    private UUID id;

    @Column(name = "name", length = 250)
    @NotBlank(message = "Course name cannot be empty")
    @NotEmpty
    private String name;

    @Column(name = "year")
    @Min(1)
    @Max(6)
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @NotNull(message = "id cannot be null")
    private Program program;

    @OneToMany(mappedBy = "course")
    private List<Class> classes;
}
