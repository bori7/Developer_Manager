package com.developer.DeveloperManager.repository;

import com.developer.DeveloperManager.model.TeamLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamLeadRepo extends JpaRepository<TeamLead, Long> {

}
