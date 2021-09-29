package com.scheduler.entity;

import com.scheduler.model.Faculty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "programs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "faculty", length = 250)
    @Enumerated(EnumType.STRING)
    private Faculty faculty;

    @OneToMany(mappedBy = "program")
    private List<Course> courses;
}
