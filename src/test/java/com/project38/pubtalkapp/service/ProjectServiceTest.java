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
import java.util.Optional;

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
        List<Artist> artist = new ArrayList<>();
        Project project = new Project(
                1L,
                "My First Project",
                "www.image1url.com",
                17,
                4321,
                artist
        );
        projectRepo.save(project);
        when(projectRepo.findById(1L)).thenReturn(Optional.of(project));

        // When
        Project returned = underTest.findProjectById(1L);

        // Then
        assertThat(returned).isEqualToComparingFieldByField(project);
        assertEquals(17, projectRepo.findById(1L).get().getNumOfTracks());

        assertNotEquals(18, projectRepo.findById(1L).get().getNumOfTracks());


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
