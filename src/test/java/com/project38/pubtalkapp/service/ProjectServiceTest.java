package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.model.Artist;
import com.project38.pubtalkapp.model.Project;
import com.project38.pubtalkapp.repo.ProjectRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepo projectRepo;
    @InjectMocks
    private ProjectService underTest;

    @Test
    void itShouldFindAllProjects() {
        // Given
        List<Artist> artist = new ArrayList<>();
        List<Project> projectList = Arrays.asList(
                new Project(
                        1L,
                        "My First Project",
                        "www.image1url.com",
                        17,
                        4321,
                        artist
                ),
                new Project(
                        2L,
                        "Back 2 Back",
                        "www.image2url.com",
                        23,
                        3412,
                        artist
                )

        );
        projectRepo.saveAll(projectList);
        when(projectRepo.findAll()).thenReturn(projectList);

        // When
        List<Project> returned = underTest.findAllProjects();

        // Then
        verify(projectRepo).saveAll(projectList);
        assertThat(returned).hasSize(2);


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
