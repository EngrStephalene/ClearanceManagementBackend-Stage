package com.clearance.management.backend.controller;

import com.clearance.management.backend.dto.OfficeDto;
import com.clearance.management.backend.entity.Offices;
import com.clearance.management.backend.service.OfficeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
@CrossOrigin("https://clearance-management-frontend-stage.vercel.app")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @GetMapping("/")
    public ResponseEntity<List<OfficeDto>> getOffices() {
        System.out.println("GET ALL OFFICES API is called.");
        List<OfficeDto> offices = officeService.getOffices();
        return new ResponseEntity<>(offices, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OfficeDto> addOffice(@RequestBody OfficeDto request) {
        System.out.println("ADD OFFICE API is called");
        OfficeDto addedOffice = officeService.addOffice(request);
        return new ResponseEntity<>(addedOffice, HttpStatus.OK);
    }

    @PatchMapping("/update-name")
    public ResponseEntity<OfficeDto> updateOfficeName(@RequestBody OfficeDto request) {
        System.out.println("UPDATE OFFICE API is called");
        OfficeDto updatedOffice = officeService.updateOffice(request);
        return new ResponseEntity<>(updatedOffice, HttpStatus.OK);
    }

    @PatchMapping("/update-assignment")
    public ResponseEntity<OfficeDto> updateOfficeAssignment(@RequestBody OfficeDto request) {
        System.out.println("UPDATE OFFICE ASSIGNMENT API is called.");
        OfficeDto updatedOffice = officeService.updateOfficeAssignment(request);
        return new ResponseEntity<>(updatedOffice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffice(@PathVariable("id") Integer id) {
        System.out.println("DELETE OFFICE API is called.");
        officeService.deleteOffice(id);
        return new ResponseEntity<>("Successfully deleted office.", HttpStatus.OK);
    }
}
