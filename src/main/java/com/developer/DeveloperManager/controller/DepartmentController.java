package com.developer.DeveloperManager.controller;


import com.developer.DeveloperManager.dto.DepartmentRequest;
import com.developer.DeveloperManager.model.Response;
import com.developer.DeveloperManager.service.implementation.DepartmentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/departmentManage")
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentService;
    Response resp = new Response();

    @PostMapping("/add")
    public Response saveDepartment(@RequestBody DepartmentRequest departmentRequest) throws ParseException {
            return  departmentService.saveDepartment(departmentRequest);
    }
    @PatchMapping("/update")
    public Response updateDepartment(@RequestBody DepartmentRequest departmentRequest) throws ParseException {
        return  departmentService.updateDepartmentName(departmentRequest);
    }
    @PostMapping("/get/{id}")
    public ResponseEntity<Response> getDepartmentById(@PathVariable("id")Long dept_Id) {
        return  ResponseEntity.ok().body(departmentService.getById(dept_Id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllDepartment() {
        return  ResponseEntity.ok().body(departmentService.getAllDepartment());
    }
    @PostMapping("/getDeptByName")
    public ResponseEntity<Response> getDepartmentByName(@RequestParam String deptName) {
        return  ResponseEntity.ok().body(departmentService.getByDeptName(deptName));
    }
    @PostMapping("/getByKeyword/{name}")
    public ResponseEntity<Response> getByKeyword(@PathVariable("name")String name) {
        return  ResponseEntity.ok().body(departmentService.getByKeyWord(name));
    }


}