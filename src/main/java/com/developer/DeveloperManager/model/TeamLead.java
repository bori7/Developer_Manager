package com.developer.DeveloperManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class TeamLead {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name ="Id",nullable = false ,unique = true, updatable = false)
    private Long lead_Id;

    @OneToOne
    @JoinColumn(name ="FK_dev_Id")
    private Developer developer;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name ="FK_dept_Id")
    private Department department;

}
