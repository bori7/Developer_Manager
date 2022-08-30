package com.developer.DeveloperManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name="Department")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"Department_Name" })})

@Data
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name ="Id", nullable = false, unique = true, updatable = false)
    private Long dept_Id;

    @Column(name="Department_Name", nullable = false, unique = false,updatable = true,columnDefinition = "TEXT")
    private String deptName;

    @Column(name="Created_Dt", nullable = false, unique = false,updatable = false,columnDefinition = "DATE")
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy="department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Developer> developer;
}
