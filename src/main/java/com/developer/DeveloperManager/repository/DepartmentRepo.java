package com.developer.DeveloperManager.repository;

import com.developer.DeveloperManager.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Optional<Department> findById(Long dept_Id);

    Optional<Department> findByDeptName(String deptName);

    List<Department> findByDeptNameContaining(String name);






}
