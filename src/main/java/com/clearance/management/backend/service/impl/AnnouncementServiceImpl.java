package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.AnnouncementDto;
import com.clearance.management.backend.dto.AnnouncementRequest;
import com.clearance.management.backend.dto.AnnouncementFormattedDateDto;
import com.clearance.management.backend.entity.Announcement;
import com.clearance.management.backend.entity.ApplicationUser;
import com.clearance.management.backend.entity.Faculty;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.AnnouncementRepository;
import com.clearance.management.backend.repository.ApplicationUserRepository;
import com.clearance.management.backend.repository.FacultyRepository;
import com.clearance.management.backend.request.UpdateAnnouncementRequest;
import com.clearance.management.backend.service.AnnouncementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AnnouncementDto addAnnouncement(AnnouncementRequest request) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setDescription(request.getDescription());
        announcementDto.setTitle(request.getTitle());
        Date currentDate = new Date();
        announcementDto.setReportedDate(currentDate);
        ApplicationUser applicationUser = applicationUserRepository.findById(request.getReporterId())
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + request.getReporterId() + " not found."));
        Faculty faculty = facultyRepository.findFacultyByUserId(applicationUser)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty with id: " + request.getReporterId() + " not found."));
        StringBuilder sb = new StringBuilder();
        sb.append(faculty.getFirstName());
        sb.append(" ");
        sb.append(faculty.getLastName());
        String reporter = sb.toString();
        announcementDto.setReporter(reporter);
        Announcement announcement = modelMapper.map(announcementDto, Announcement.class);
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return modelMapper.map(savedAnnouncement, AnnouncementDto.class);
    }

    @Override
    public List<AnnouncementFormattedDateDto> getAllAnnouncement() {
        List<Announcement> announcementList = announcementRepository.findAllByOrderByReportedDateDesc();
        List<AnnouncementDto> announcementDtoList = announcementList
                .stream()
                .map((announcement) -> modelMapper.map(announcement, AnnouncementDto.class))
                .toList();
        List<AnnouncementFormattedDateDto> announcementFormattedDateDtoList = new ArrayList<>();
        for(AnnouncementDto announcementDto: announcementDtoList) {
            AnnouncementFormattedDateDto announcementFormattedDateDto = buildAnnouncementResponse(announcementDto);
            announcementFormattedDateDtoList.add(announcementFormattedDateDto);
        }
        return announcementFormattedDateDtoList;
    }

    private AnnouncementFormattedDateDto buildAnnouncementResponse(AnnouncementDto announcementDto) {
        AnnouncementFormattedDateDto announcementFormattedDateDto = new AnnouncementFormattedDateDto();
        String reportedDateStr = formatReportedDate(announcementDto.getReportedDate());
        announcementFormattedDateDto.setId(announcementDto.getId());
        announcementFormattedDateDto.setTitle(announcementDto.getTitle());
        announcementFormattedDateDto.setDescription(announcementDto.getDescription());
        announcementFormattedDateDto.setReportedDate(reportedDateStr);
        announcementFormattedDateDto.setReporter(announcementDto.getReporter());
        return  announcementFormattedDateDto;
    }

    private String formatReportedDate(Date reportedDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd , yyyy");
        return formatter.format(reportedDate);
    }

    @Override
    public AnnouncementDto getAnnouncementById(Integer id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement with id: " + id + " not found."));
        return modelMapper.map(announcement, AnnouncementDto.class);
    }

    @Override
    public AnnouncementDto updateAnnouncement(UpdateAnnouncementRequest request) {
        Announcement announcement = announcementRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Announcement with id: " + request.getId() + " not found."));
        announcement.setTitle(request.getTitle());
        announcement.setDescription(request.getDescription());
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
