package com.clearance.management.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "offices")
public class Offices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "isOfficeHeadAssigned")
    private Integer isOfficeHeadAssigned;

    public Offices() {
    }

    public Offices(String name, Integer isOfficeHeadAssigned) {
        this.name = name;
        this.isOfficeHeadAssigned = isOfficeHeadAssigned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsOfficeHeadAssigned() {
        return isOfficeHeadAssigned;
    }

    public void setIsOfficeHeadAssigned(Integer isOfficeHeadAssigned) {
        this.isOfficeHeadAssigned = isOfficeHeadAssigned;
    }
}
