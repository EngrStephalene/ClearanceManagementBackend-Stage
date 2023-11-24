package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.AnnouncementDto;
import com.clearance.management.backend.dto.AnnouncementRequest;

import java.util.List;

public interface AnnouncementService {
    public AnnouncementDto addAnnouncement(AnnouncementRequest request);
    public List<AnnouncementDto> getAllAnnouncement();
    public AnnouncementDto getAnnouncementById(Integer id);
    public AnnouncementDto updateAnnouncement(AnnouncementDto request, Integer id);

    public void deleteAnnouncement(Integer id);
}
