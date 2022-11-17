package com.project38.pubtalkapp.service;

import com.project38.pubtalkapp.model.Project;
import com.project38.pubtalkapp.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public List<Project> findAllProjects() {
        return projectRepo.findAll();
    }

    public Project findProjectById(Long id) {
        return null;
    }

    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    public Project editProject(Project project) {
        return projectRepo.save(project);
    }

    public void deleteProjectById(Long id) {
        projectRepo.deleteProjectById(id);
    }
}
