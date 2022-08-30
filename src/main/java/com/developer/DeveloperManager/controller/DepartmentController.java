package com.developer.DeveloperManager.controller;


import com.developer.DeveloperManager.dto.DepartmentRequest;
import com.developer.DeveloperManager.model.Department;
import com.developer.DeveloperManager.model.Developer;
import com.developer.DeveloperManager.model.Response;
import com.developer.DeveloperManager.service.implementation.DepartmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departmentManage")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;
    Response resp = new Response();

    @PostMapping("/add")
    public Response saveDepartment(@RequestBody DepartmentRequest departmentRequest) throws ParseException {
            return  departmentService.saveDepartment(departmentRequest);
    }

    @GetMapping("/getAll")
    public Response getAllDepartment() {
        return  departmentService.getAllDepartment();

    }
    /*@PostMapping("/getAllDeveloper")
    public Response getDeveloperByDeptId(@RequestParam String dev_Id) {
        return  departmentService.getDeveloperById(dev_Id);
    }*/
}