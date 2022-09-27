package com.developer.DeveloperManager.service;

import com.developer.DeveloperManager.dto.DepartmentRequest;
import com.developer.DeveloperManager.dto.TeamLeadRequest;
import com.developer.DeveloperManager.model.Response;

import java.text.ParseException;

public interface TeamLeadService {
   Response saveTeamLead(TeamLeadRequest teamLeadRequest) ;
    Response updateTeamLead(TeamLeadRequest teamLeadRequest);
    Response getAllTeamLead();
    Response getTeamLeadByDeptId(Long dept_Id);
    Response getTeamLeadById(Long lead_Id);


    }