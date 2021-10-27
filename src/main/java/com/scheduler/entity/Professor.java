package com.scheduler.entity;

import com.scheduler.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "professors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "name", length = 250)
    private String name;

    @NotBlank(message = "Position cannot be empty")
    @Column(name = "position", length = 250)
    @Enumerated(EnumType.STRING)
    private Position position;

    @NotBlank(message = "Classes list cannot be empty")
    @OneToMany(mappedBy = "course")
    private List<Class> classes;
}
