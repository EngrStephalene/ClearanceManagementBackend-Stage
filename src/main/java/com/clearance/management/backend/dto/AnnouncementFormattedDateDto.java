package com.clearance.management.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementFormattedDateDto {

    private Integer id;
    private String title;
    private String description;
    private String reportedDate;
    private String reporter;

    public AnnouncementFormattedDateDto() {
    }
}
