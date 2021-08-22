package com.neosono.springboot.assignment.repository;

import com.neosono.springboot.assignment.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
  }

