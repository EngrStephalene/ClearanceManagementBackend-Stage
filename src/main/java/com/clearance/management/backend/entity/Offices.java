package com.clearance.management.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "offices")
public class Offices {

    @Column(name = "id")
    private Integer id;

    @Column(name = "office")
    private String office;

    @Column(name = "isTaken")
    private Integer isTaken;

    public Offices() {
    }

    public Offices(String office) {
        this.office = office;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(Integer isTaken) {
        this.isTaken = isTaken;
    }
}
