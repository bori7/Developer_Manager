package com.developer.DeveloperManager.controller;

import com.developer.DeveloperManager.dto.DeveloperRequest;
import com.developer.DeveloperManager.model.Department;
import com.developer.DeveloperManager.model.Response;
import com.developer.DeveloperManager.service.DepartmentService;
import com.developer.DeveloperManager.service.DeveloperService;
import com.developer.DeveloperManager.service.implementation.DeveloperServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/developerManage")
public class DeveloperController {

    private final DeveloperService developerServiceImpl;
    private final DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<Response> addDeveloper(@RequestBody DeveloperRequest developerRequest) throws ParseException {
        return ResponseEntity.ok().body(developerServiceImpl.saveDeveloper(developerRequest));
    }

    @PatchMapping("/update")
    public ResponseEntity<Response> updateDeveloper(@RequestBody DeveloperRequest developerRequest) throws ParseException {
        return ResponseEntity.ok().body(developerServiceImpl.updateDeveloperDetails(developerRequest));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok().body(developerServiceImpl.getAllDevelopers());
    }

    @PostMapping("/getByDeptId")
    public ResponseEntity<Response> getByDepartment(@RequestParam Long dept_Id) {
        return ResponseEntity.ok().body(departmentService.getDeveloperByDept(dept_Id));

    }

    @PostMapping("/getByGender")
    public ResponseEntity<Response> getByGender(@RequestParam String gender) {
        return ResponseEntity.ok().body(developerServiceImpl.getByGender(gender));
    }

}
