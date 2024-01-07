package com.clearance.management.backend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAnnouncementRequest {
    private Integer id;
    private String title;
    private String description;

    public UpdateAnnouncementRequest() {
    }
}
