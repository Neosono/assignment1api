package com.neosono.springboot.assignment.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosono.springboot.assignment.exceptions.DeveloperNotFoundException;
import com.neosono.springboot.assignment.model.Developer;
import com.neosono.springboot.assignment.model.DeveloperSkills;
import com.neosono.springboot.assignment.service.DeveloperService;
import com.neosono.springboot.assignment.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@RestController
@RequestMapping("/api")
public class DeveloperController {
    @Autowired
    DeveloperService service;

    private static final Logger logger = LoggerFactory.getLogger(DeveloperController.class);
    /*
     * List All clients
     */
    @GetMapping("/developers")
    public ResponseEntity getDevelopers()throws DeveloperNotFoundException {
        logger.info("ENTER: GET/RETRIEVE  DEVELOPERS!");
        List<Developer> developerList = service.getDeveloperList();
        logger.info("EXIT: GET/RETRIEVE  DEVELOPERS!");
        return ( ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(developerList));
    }

    @PutMapping(value = "/developers/{id}")
    public ResponseEntity updateDeveloper(@PathVariable Long id, @RequestBody String json)throws DeveloperNotFoundException{

        try {
            logger.info("ENTER: UPDATE  DEVELOPER!");
            ObjectMapper mapper = new ObjectMapper();
            final JsonNode data = mapper.readTree(json);
            logger.info("received developer json String " + json);
            String name = data.get("name").asText();
            List<Developer> developerList = service.getDeveloperList();
            for (Developer dev : developerList) {
                if (id == dev.getId()) {
                    Developer developer = dev;
                    developer.setName(name);
                    developer.setId(id);
                    service.addDeveloper(developer);
                    break;
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Developer UNsuccessfully created!!!");
        }
        logger.info("EXIT: UPDATE  DEVELOPER!");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body("Developer is updated successfully !!!");
    }
    /*
     * destroy  developer
     * @param dto
     */
    @DeleteMapping("/developers/{id}")
    public ResponseEntity destroyDeveloper(@PathVariable Long id) throws DeveloperNotFoundException{
        try {
            logger.info("ENTER: DESTROY  DEVELOPER!");
            service.destroyDeveloper(id);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
        logger.info("EXIT: DESTROY  DEVELOPER!");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body("Developer is deleted successfully ");
    }
    /*
     * add developer
     */
    @PostMapping(value="/developers")
    public ResponseEntity createDeveloper(@RequestBody String json) throws IOException, DeveloperNotFoundException {
        try {
            logger.info("ENTER: CREATE DEVELOPER");
            ObjectMapper mapper = new ObjectMapper();
            final JsonNode data = mapper.readTree(json);
            logger.info("received developer json String " + json);

            String name = data.get("name").asText();
            String skillnamesList = data.get("skills").asText();
            String customStringTokens = "," + skillnamesList + ",";
            Developer dev = new Developer();
            StringTokenizer st = new StringTokenizer(customStringTokens,",");
            List<DeveloperSkills> developerSkillsList = new ArrayList<>();
            DeveloperSkills developerSkill = new DeveloperSkills();
            String strSkillName = "";
            List<String> strList = new ArrayList<>();
            while(st.hasMoreTokens()){
                developerSkill = new DeveloperSkills();
                developerSkill.setSkillName(st.nextToken());
                developerSkillsList.add(developerSkill);
            }
            ////////////////////
            dev.setName(name);
            dev.setSkills(developerSkillsList);
            service.addDeveloper(dev);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
        logger.info("EXIT: CREATE DEVELOPER!");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body("Developer is created successfully ");
    }
}