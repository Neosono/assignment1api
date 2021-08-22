package com.neosono.springboot.assignment.repository;

import com.neosono.springboot.assignment.model.DeveloperSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperSkillsRepository extends JpaRepository<DeveloperSkills, Long> {

}
