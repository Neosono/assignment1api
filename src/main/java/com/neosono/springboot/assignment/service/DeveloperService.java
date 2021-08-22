package com.neosono.springboot.assignment.service;

import com.neosono.springboot.assignment.model.Developer;
import com.neosono.springboot.assignment.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperService {

  @Autowired
  DeveloperRepository repo;

  public List<Developer> getDeveloperList(){
    return repo.findAll();
  }
  public void updateDeveloper(Developer dev) {
     repo.save(dev);
  }
  public void destroyDeveloper(Long id) {
     repo.deleteById(id);
  }
  public void addDeveloper(Developer dev) {
    repo.save(dev); }
}
