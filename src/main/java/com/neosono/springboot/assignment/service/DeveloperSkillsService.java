package com.neosono.springboot.assignment.service;


import com.neosono.springboot.assignment.model.DeveloperSkills;
import com.neosono.springboot.assignment.repository.DeveloperSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeveloperSkillsService {
    @Autowired
    DeveloperSkillsRepository repo;
    public void addSkill(DeveloperSkills skill) { repo.save(skill); }
}
