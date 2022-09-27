package com.developer.DeveloperManager.service;

import com.developer.DeveloperManager.dto.DepartmentRequest;
import com.developer.DeveloperManager.dto.DeveloperRequest;
import com.developer.DeveloperManager.model.Response;

import java.text.ParseException;

public interface DepartmentService {
    Response saveDepartment(DepartmentRequest departmentRequest) throws ParseException;

    Response updateDepartmentName(DepartmentRequest departmentRequest) throws ParseException;

    Response getAllDepartment();
    Response getById(Long dept_Id);
    Response getByDeptName(String dept_Name);
    Response getByKeyWord(String name);

    Response getDeveloperByDept(Long dept_Id);



//    Response getDeveloperById(Long dept_Id);


}
