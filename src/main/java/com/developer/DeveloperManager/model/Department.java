package com.developer.DeveloperManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name="Department")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"Department_Name" })})
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name ="Id",nullable = false ,unique = true, updatable = false)
    private Long dept_Id;

    @Column(name="Department_Name",unique = true, nullable = false,updatable = true,columnDefinition = "TEXT")
    private String deptName;

    @Column(name="Created_Dt", nullable = false ,updatable = false,columnDefinition = "DATE")
    private LocalDateTime dateCreated;

    @Column(name="Modified_Dt")
    private LocalDateTime dateModified;

    @OneToMany(mappedBy = "department" ,cascade = CascadeType.ALL)
    private List<Developer> developer;

    @OneToOne(mappedBy = "department")
    private TeamLead teamLead;

    @Override
    public String toString() {
        return "Department{" +
                "dept_Id=" + dept_Id +
                ", deptName='" + deptName + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                '}';
    }

    public Long getDept_Id() {
        return dept_Id;
    }

    public void setDept_Id(Long dept_Id) {
        this.dept_Id = dept_Id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public List<Developer> getDeveloper() {
        return developer;
    }

    public void setDeveloper(List<Developer> developer) {
        this.developer = developer;
    }

    public TeamLead getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(TeamLead teamLead) {
        this.teamLead = teamLead;
    }
}
