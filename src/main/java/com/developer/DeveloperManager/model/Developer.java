package com.developer.DeveloperManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Developer")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"Email", "Phone"})})
@Data
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false, unique = true, updatable = false)
    private Long dev_Id;

    @Column(name = "Surname", nullable = false, unique = false, updatable = true, columnDefinition = "TEXT")
    private String surName;

    @Column(name = "First_Name", nullable = false, unique = false, updatable = false, columnDefinition = "TEXT")
    private String firstName;


    @Column(name = "Middle_Name", nullable = false, unique = false, updatable = false, columnDefinition = "TEXT")
    private String middleName;

    @Column(name = "Gender", nullable = false, unique = false, updatable = false, columnDefinition = "TEXT")
    private String gender;

    @Column(name = "Date_Of_Birth", nullable = false, unique = false, updatable = false, columnDefinition = "TEXT")
    private String dateOfBirth;

    @Column(name = "Email", nullable = false, unique = true, updatable = true, length = 100)
    private String email;

    @Column(name = "Phone", nullable = false, unique = true, updatable = true, length = 50)
    private String phoneNo;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FK_dept_Id")
    private Department department;

    @Column(name = "SkillSet", nullable = false, unique = false, updatable = true, columnDefinition = "TEXT")
    private String skills;
@JsonIgnore
    @OneToOne
    @JoinColumn(name = "FK_lead_Id")
    private TeamLead teamLead;


    @Column(name = "Created_Dt", nullable = false, unique = false, updatable = false, columnDefinition = "DATE")
    private LocalDateTime dateCreated;

    @Column(name = "Modified_Dt", unique = false, updatable = true, columnDefinition = "DATE")
    private LocalDateTime dateModified;


    @Override
    public String toString() {
        return "Developer{" +
                "dev_Id=" + dev_Id +
                ", surName='" + surName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", skills='" + skills + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                '}';
    }
}

