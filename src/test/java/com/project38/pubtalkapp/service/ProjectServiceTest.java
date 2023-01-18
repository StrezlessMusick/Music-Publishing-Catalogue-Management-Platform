package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.repo.ProjectRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepo projectRepo;
    @InjectMocks
    private ProjectService underTest;

    @Test
    void itShouldFindAllProjects() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldFindProjectById() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldThrowProjectNotFoundException() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldCreateProject() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldEditProject() {
        // Given

        // When

        // Then

    }

    @Test
    void itShouldDeleteProjectById() {
        // Given

        // When

        // Then

    }
}
