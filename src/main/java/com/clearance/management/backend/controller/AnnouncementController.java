package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.AnnouncementDto;
import com.clearance.management.backend.dto.AnnouncementRequest;
import com.clearance.management.backend.dto.AnnouncementFormattedDateDto;
import com.clearance.management.backend.request.UpdateAnnouncementRequest;
import com.clearance.management.backend.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
@CrossOrigin("https://clearance-management-frontend-stage.vercel.app")
@AllArgsConstructor
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PostMapping("/add")
    public ResponseEntity<AnnouncementDto> addAnnouncement(@RequestBody AnnouncementRequest request) {
        System.out.println("ADD announcement api is called.");
        AnnouncementDto announcementDto = announcementService.addAnnouncement(request);
        return new ResponseEntity<>(announcementDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("/")
    public ResponseEntity<List<AnnouncementFormattedDateDto>> getAllAnnouncement() {
        System.out.println("GET ALL announcement api is called.");
        List<AnnouncementFormattedDateDto> announcementDtoList = announcementService.getAllAnnouncement();
        return new ResponseEntity<>(announcementDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            " 'STUDENT', 'STUDENT_SG_PRESIDENT', 'STUDENT_DEPARTMENT_GOV', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @GetMapping("{id}")
    public ResponseEntity<AnnouncementDto> getAnnouncementById(@PathVariable("id") Integer id) {
        System.out.println("GET announcement api is called.");
        AnnouncementDto announcementDto = announcementService.getAnnouncementById(id);
        return new ResponseEntity<>(announcementDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @PutMapping("/update")
    public ResponseEntity<AnnouncementDto> updateAnnouncementDto(@RequestBody UpdateAnnouncementRequest request) {
        System.out.println("UPDATE announcement api is called.");
        AnnouncementDto announcementDto = announcementService.updateAnnouncement(request);
        return new ResponseEntity<>(announcementDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY'," +
            " 'CAMPUS_MINISTRY', 'GUIDANCE_OFFICE', 'LIBRARIAN', 'DISPENSARY', " +
            "'PROPERTY_CUSTODIAN', 'PREFECT_DISCIPLINE', 'REGISTRAR', " +
            "'FINANCE', 'COLLEGE_DEAN', 'SCHOOL_DIRECTOR', 'DEPARTMENT_CHAIRMAN', 'SG_ADVISER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAnnouncementDto(@PathVariable("id") Integer id) {
        System.out.println("DELETE announcement api is called.");
        announcementService.deleteAnnouncement(id);
        return new ResponseEntity<>("Announcement successfully deleted.", HttpStatus.OK);
    }

}
