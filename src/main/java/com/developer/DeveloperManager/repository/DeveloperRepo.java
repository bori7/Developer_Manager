package com.developer.DeveloperManager.repository;

import com.developer.DeveloperManager.model.Department;
import com.developer.DeveloperManager.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepo  extends JpaRepository<Developer,Long> {
    List<Developer> findByDepartment(Department dept_Id);
    Optional<List<Developer>>findByGender(String gender);
    Optional<Developer> findByEmail(String email);
    Optional<Developer> findByPhoneNo(String phoneNo);



}
