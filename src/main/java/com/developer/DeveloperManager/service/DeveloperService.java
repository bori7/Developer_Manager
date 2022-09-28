package com.developer.DeveloperManager.service;

import com.developer.DeveloperManager.dto.DeveloperRequest;
import com.developer.DeveloperManager.model.Department;
import com.developer.DeveloperManager.model.Response;

import java.text.ParseException;

public interface DeveloperService {

    Response saveDeveloper(DeveloperRequest developerRequest) throws ParseException;
    Response updateDeveloperDetails(DeveloperRequest developerRequest);
    Response getAllDevelopers();
    Response getByGender(String gender);
    Response getByDepartment(Long dept_Id);

    Response getDeveloperById(Long dev_Id);


    /*Response getByDepartment(String deptName);*/


}
