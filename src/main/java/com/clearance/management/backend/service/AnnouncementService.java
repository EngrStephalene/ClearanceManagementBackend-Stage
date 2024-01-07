package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.AnnouncementDto;
import com.clearance.management.backend.dto.AnnouncementRequest;
import com.clearance.management.backend.dto.AnnouncementFormattedDateDto;
import com.clearance.management.backend.request.UpdateAnnouncementRequest;

import java.util.List;

public interface AnnouncementService {
    public AnnouncementDto addAnnouncement(AnnouncementRequest request);
    public List<AnnouncementFormattedDateDto> getAllAnnouncement();
    public AnnouncementDto getAnnouncementById(Integer id);
    public AnnouncementDto updateAnnouncement(UpdateAnnouncementRequest request);

    public void deleteAnnouncement(Integer id);
}
