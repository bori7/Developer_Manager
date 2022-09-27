package com.developer.DeveloperManager.dto;

import com.developer.DeveloperManager.model.Department;
import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
@Data
public class DeveloperRequest {

    private Long dev_Id;
    private String firstName;
    private String surName;
    private String middleName;
    private String phoneNo;
    private String email;
    private Department department;
    private String dateOfBirth;
    private String skills;
    private String gender;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;


}

