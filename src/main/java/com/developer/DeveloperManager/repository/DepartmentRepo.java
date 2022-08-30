package com.developer.DeveloperManager.repository;

import com.developer.DeveloperManager.model.Department;
import com.developer.DeveloperManager.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Optional<Department> findById(Long departmentId);


}
