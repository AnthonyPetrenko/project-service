package com.example.projectservice.kafka;

import com.example.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProjectIdConsumer {

    private final ProjectService projectService;

    @Autowired
    public ProjectIdConsumer(ProjectService projectService) {
        this.projectService = projectService;
    }

    @KafkaListener(topics = "testTopic", groupId = "group_id")
    public void getMessage(long projectId) {
        projectService.changeStatus(projectId);
    }


}
