package com.developer.DeveloperManager.service;

import com.developer.DeveloperManager.dto.DepartmentRequest;
import com.developer.DeveloperManager.dto.DeveloperRequest;
import com.developer.DeveloperManager.model.Response;

import java.text.ParseException;

public interface DepartmentService {
    Response saveDepartment(DepartmentRequest departmentRequest) throws ParseException;
    Response getAllDepartment();

/*
    Response getDeveloperById(String dept_Id);
*/

}
