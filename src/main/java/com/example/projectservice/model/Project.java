package com.example.projectservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "project")
@Setter
@Getter
@NoArgsConstructor
public class Project {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "status")
    private String projectStatus = "DRAFT";

    public Project(String projectName){
        this.projectName = projectName;
    }
}
