package com.example.projectservice.service;

import com.example.projectservice.feign_client.AccountFeignClient;
import com.example.projectservice.model.Project;
import com.example.projectservice.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final AccountFeignClient accountFeignClient;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          AccountFeignClient accountFeignClient) {
        this.projectRepository = projectRepository;
        this.accountFeignClient = accountFeignClient;
    }

    public boolean createProject(String projectName) {
        projectName = (projectName == null || projectName.isEmpty()) ? "N/A" : projectName;

        try{
            projectRepository.save(new Project(projectName));
            log.info("Project " + projectName + " has been created.");
            return true;
        } catch (DataAccessException exception){
            log.error("Error while saving project: " + exception.getMessage());
            return false;
        }
    }

    @Transactional
    public void changeStatus(long projectId) {
        int isChanged = projectRepository.updateStatusByProjectId(projectId);

        String logMessage = isChanged == 1
                ? String.format("Project %d status changed to 'REGISTERED'.", projectId)
                : String.format("Project %d doesn't exist.", projectId);

        log.info(logMessage);
    }

    @Transactional
    public boolean deleteProjectById(long projectId) {
        int isDeleted = projectRepository.deleteProjectByProjectId(projectId);

        String logMessage = isDeleted > 0
                ? String.format("Project with project ID '%d' has been deleted.", projectId)
                : String.format("Project with project ID '%d' doesn't exist.", projectId);

        log.info(logMessage);

        sendDataByFeignClient(projectId);

        return isDeleted > 0;
    }

    public boolean checkIfProjectExist(long projectId) {
        return projectRepository.existsByProjectId(projectId);
    }

    private void sendDataByFeignClient(long projectId){
        accountFeignClient.removeAllProjectId(projectId);
    }

}