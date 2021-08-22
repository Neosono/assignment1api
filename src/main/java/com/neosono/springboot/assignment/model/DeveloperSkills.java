package com.neosono.springboot.assignment.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="developer_skills")
public class DeveloperSkills implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "skill_name")
    private String skillName;
    public DeveloperSkills(){

    }

    public DeveloperSkills(String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeveloperSkills)) return false;
        DeveloperSkills that = (DeveloperSkills) o;
        return getId().equals(that.getId()) && Objects.equals(getSkillName(), that.getSkillName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSkillName());
    }

    @Override
    public String toString() {
        return "DeveloperSkills{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
