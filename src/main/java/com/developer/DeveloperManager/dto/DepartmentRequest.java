package com.developer.DeveloperManager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentRequest {
    private Long dept_Id;
    private String deptName;
    private LocalDateTime dateCreated;
    @JsonIgnore
    private LocalDateTime dateModified;

}
