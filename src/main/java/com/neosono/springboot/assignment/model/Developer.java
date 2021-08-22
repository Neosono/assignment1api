package com.neosono.springboot.assignment.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="developers")
public class Developer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "developer_name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "devId")
  private List<DeveloperSkills> skills;

  public Developer() {
  }

  public Developer(String fName) {
    this.name = fName;

  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String fName) {
    this.name = fName;
  }


  public List<DeveloperSkills> getSkills() {
    return skills;
  }

  public void setSkills(List<DeveloperSkills> developerSkills) {
    this.skills = developerSkills;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Developer)) return false;
    Developer developer = (Developer) o;
    return getId().equals(developer.getId()) && Objects.equals(getName(), developer.getName()) && Objects.equals(getSkills(), developer.getSkills());
  }

  @Override
  public String toString() {
    return "Developer{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", skills=" + skills +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getSkills());
  }
}

