package com.developer.DeveloperManager.dto;

import com.developer.DeveloperManager.model.Department;
import com.developer.DeveloperManager.model.Developer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TeamLeadRequest {
    private Long lead_Id;
    private Department department;
    private Developer developer;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

}
