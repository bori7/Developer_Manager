package com.developer.DeveloperManager.repository;

import com.developer.DeveloperManager.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Developer, Long> {
}
