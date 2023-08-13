package com.example.projectservice.repository;

import com.example.projectservice.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsByProjectId(long projectId);

    @Modifying
    @Query("UPDATE Project pr SET pr.projectStatus='REGISTERED' WHERE pr.projectId =?1")
    int updateStatusByProjectId(long projectId);

    int deleteProjectByProjectId(long projectId);

}
