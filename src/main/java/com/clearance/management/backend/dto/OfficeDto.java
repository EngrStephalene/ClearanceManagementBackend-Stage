package com.clearance.management.backend.dto;

public class OfficeDto {
    Integer id;
    private String name;
    private Integer isOfficeHeadAssigned;

    public OfficeDto() {
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
