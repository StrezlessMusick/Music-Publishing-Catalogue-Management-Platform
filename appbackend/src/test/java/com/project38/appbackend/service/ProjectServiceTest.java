package com.project38.appbackend.service;

import com.project38.appbackend.exception.ProjectNotFoundException;
import com.project38.appbackend.model.Artist;
import com.project38.appbackend.model.Project;
import com.project38.appbackend.repo.ProjectRepo;
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
import static org.mockito.Mockito.*;

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
                        artist,
                        null
                ),
                new Project(
                        2L,
                        "Back 2 Back",
                        "www.image2url.com",
                        23,
                        3412,
                        artist,
                        null
                )
        );

        // When
        when(projectRepo.findAll()).thenReturn(projectList);

        // Then
        List<Project> returned = underTest.findAllProjects();
        assertThat(returned).hasSize(2);
    }

    @Test
    void itShouldFindProjectById() {
        // Given
        List<Artist> artist = new ArrayList<>();
        Long id = 1L;
        Project project = new Project(
                id,
                "My First Project",
                "www.image1url.com",
                17,
                4321,
                artist,
                null
        );
        Optional<Project> projOpt = Optional.of(project);
        when(projectRepo.findById(id)).thenReturn(projOpt);

        // When
        Project returned = underTest.findProjectById(id);

        // Then
        assertThat(returned).isEqualToComparingFieldByField(project);
    }

    @Test
    void itShouldThrowProjectNotFoundException() {
        // Given
        List<Artist> artist = new ArrayList<>();
        Long id = 1L;
        Project project = new Project(
                id,
                "My First Project",
                "www.image1url.com",
                17,
                4321,
                artist,
                null
        );
        Optional<Project> projOpt = Optional.of(project);
        when(projectRepo.findById(id)).thenReturn(projOpt);

        // When & Then
        assertEquals(projOpt.get().getNumOfTracks(), projectRepo.findById(id).get().getNumOfTracks());
        assertThrows(ProjectNotFoundException.class, () -> underTest.findProjectById(2L));

        Long tcId = 3L;
        try {
            projectRepo.findById(tcId);
        } catch (ProjectNotFoundException e) {
            assertEquals(String.format("Project with id [%s] not found.", tcId),
                    e.getMessage());
        }
    }

    @Test
    void itShouldCreateProject() {
        // Given
        List<Artist> artist = new ArrayList<>();
        Long id = 1L;
        Project project = new Project(
                id,
                "My First Project",
                "www.image1url.com",
                17,
                4321,
                artist,
                null
        );
        when(projectRepo.save(project)).thenReturn(project);

        // When
        Project returned = underTest.createProject(project);

        // Then
        verify(projectRepo).save(project);
        assertThat(returned).isEqualToComparingFieldByField(project);
    }

    @Test
    void itShouldEditExistingProject() {
        // Given
        List<Artist> artist = new ArrayList<>();
        Long id = 1L;
        Project project = new Project(
                id,
                "My First Project",
                "www.image1url.com",
                17,
                4321,
                artist,
                null
        );
        projectRepo.save(project);

        Optional<Project> projOpt = Optional.of(project);
        when(projectRepo.findById(id)).thenReturn(projOpt);

        // When
        Project projEdit = new Project(
                id,
                "First Up",
                "www.image1url.com",
                19,
                2743,
                artist,
                null
        );
        underTest.editProject(projEdit);

        Optional<Project> projEditOpt = Optional.of(projEdit);
        when(projectRepo.findById(id)).thenReturn(projEditOpt);

        // Then
        verify(projectRepo).save(project);
        assertEquals(projEdit.getProjectName(), projectRepo.findById(1L).get().getProjectName());

        assertNotEquals(Optional.empty(), projectRepo.findById(1L));
        assertNotEquals(project.getProjectLength(), projectRepo.findById(1L).get().getProjectLength());
    }

    @Test
    void itShouldDeleteProjectById() {
        // Given
        List<Artist> artist = new ArrayList<>();
        Project project = new Project(
                1L,
                "My First Project",
                "www.image1url.com",
                17,
                4321,
                artist,
                null
        );
        Project realProject = new Project(
                2L,
                "First Up",
                "www.image1url.com",
                19,
                2743,
                artist,
                null
        );
        projectRepo.saveAll(Arrays.asList(project, realProject));

        // When
        underTest.deleteProjectById(1L);

        when(projectRepo.findById(1L)).thenReturn(Optional.empty());
        when(projectRepo.findById(2L)).thenReturn(Optional.of(realProject));

        // Then
        verify(projectRepo, times(1)).deleteById(1L);

        assertEquals(Optional.empty(), projectRepo.findById(1L));
        assertEquals(realProject.getNumOfTracks(), projectRepo.findById(2L).get().getNumOfTracks());
    }

}
