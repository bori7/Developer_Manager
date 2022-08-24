package com.developer.DeveloperManager.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name="Department")
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name ="Id", nullable = false, unique = true, updatable = false)
    private Long dept_Id;

    @Column(name="Department_Name", nullable = false, unique = false,updatable = true,columnDefinition = "TEXT")
    private String deptName;

    @OneToMany(mappedBy="department")
    private Set<Developer> developer;
}
