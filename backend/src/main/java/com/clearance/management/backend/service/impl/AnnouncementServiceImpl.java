package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.AnnouncementDto;
import com.clearance.management.backend.dto.AnnouncementRequest;
import com.clearance.management.backend.entity.Announcement;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.AnnouncementRepository;
import com.clearance.management.backend.service.AnnouncementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AnnouncementDto addAnnouncement(AnnouncementRequest request) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setDescription(request.getDescription());
        announcementDto.setTitle(request.getTitle());
        announcementDto.setSubDescription(announcementDto.getSubDescription());
        Date currentDate = new Date();
        announcementDto.setReportedDate(currentDate);
        Announcement announcement = modelMapper.map(announcementDto, Announcement.class);
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return modelMapper.map(savedAnnouncement, AnnouncementDto.class);
    }

    @Override
    public List<AnnouncementDto> getAllAnnouncement() {
        List<Announcement> announcementList = announcementRepository.findAll();
        return announcementList
                .stream()
                .map((announcement) -> modelMapper.map(announcement, AnnouncementDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AnnouncementDto getAnnouncementById(Integer id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement with id: " + id + " not found."));
        return modelMapper.map(announcement, AnnouncementDto.class);
    }

    @Override
    public AnnouncementDto updateAnnouncement(AnnouncementDto request, Integer id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement with id: " + id + " not found."));
        announcement.setTitle(request.getTitle());
        announcement.setDescription(request.getDescription());
        announcement.setSubDescription(request.getSubDescription());
        Announcement updatedAnnouncement = announcementRepository.save(announcement);
        return modelMapper.map(updatedAnnouncement, AnnouncementDto.class);
    }

    @Override
    public void deleteAnnouncement(Integer id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement with id: " + id + " not found."));
        announcementRepository.deleteById(id);
    }
}
