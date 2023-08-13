package com.example.projectservice.controller;

import com.example.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProject
            (@RequestParam(name = "project-name", required = false) String projectName) {

        projectName = projectName == null ? "N/A" : projectName;
        projectService.createProject(projectName);

        return ResponseEntity.ok("Project has been created.");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteProjectById
            (@RequestParam("project-id") long projectId){

        if (projectService.deleteProjectById(projectId)){
            return ResponseEntity
                    .ok("Project with project ID " + projectId + " has been deleted.");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Project with project ID " + projectId + " doesn't exist.");
        }
    }

    @PostMapping("/change-status")
    public void changeProjectStatus(@RequestParam ("project-id") long projectId) {
        projectService.changeStatus(projectId);
    }

    @GetMapping("/check-project")
    public boolean checkIfProjectExist(@RequestParam ("project-id") long projectId) {
        return projectService.checkIfProjectExist(projectId);
    }

}