package com.project38.appbackend.service;

import com.project38.appbackend.exception.ProjectNotFoundException;
import com.project38.appbackend.model.Project;
import com.project38.appbackend.repo.ProjectRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProjectService {

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public List<Project> findAllProjects() {
        return projectRepo.findAll();
    }

    public Project findProjectById(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(
                        String.format("Project with id [%s] not found.", id)
                ));
    }

    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    public Project editProject(Project project) {
        return projectRepo.save(project);
    }

    public void deleteProjectById(Long id) {
        projectRepo.deleteById(id);
    }

    private final ProjectRepo projectRepo;
}
