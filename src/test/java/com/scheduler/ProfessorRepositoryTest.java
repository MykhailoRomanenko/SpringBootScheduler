package com.scheduler;


import static org.assertj.core.api.Assertions.assertThat;

import com.scheduler.entity.Professor;
import com.scheduler.model.Position;
import com.scheduler.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProfessorRepositoryTest {

    @Autowired
    ProfessorRepository repository;

    @Test
    public void should_find_no_tutorials_if_repository_is_empty() {
        Iterable<Professor> professors = repository.findAll();

        assertThat(professors).isEmpty();
    }

    @Test
    public void should_store_a_professor() {
        Professor professor = new Professor();
        professor.setName("Vasya");
        professor.setPosition(Position.ASSISTANT);

        Professor professorFromRepo = repository.save(professor);

        assertThat(professorFromRepo).hasFieldOrPropertyWithValue("name", "Vasya");
        assertThat(professorFromRepo).hasFieldOrPropertyWithValue("position", Position.ASSISTANT);
    }
}
