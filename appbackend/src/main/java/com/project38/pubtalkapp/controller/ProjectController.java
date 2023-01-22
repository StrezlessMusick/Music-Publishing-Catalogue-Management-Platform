package com.project38.pubtalkapp.controller;

import com.project38.pubtalkapp.model.Project;
import com.project38.pubtalkapp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getProjects() {
        List<Project> allProjects = projectService.findAllProjects();
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @GetMapping("/find{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id) {
        Project projectById = projectService.findProjectById(id);
        return new ResponseEntity<>(projectById, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project newProject) {
        Project createdProject = projectService.createProject(newProject);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        Project editedProject = projectService.editProject(project);
        return new ResponseEntity<>(editedProject, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> removeProject(@PathVariable("id") Long id) {
        projectService.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
