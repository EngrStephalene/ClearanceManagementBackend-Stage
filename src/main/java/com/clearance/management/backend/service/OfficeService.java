package com.clearance.management.backend.service;

import com.clearance.management.backend.dto.OfficeDto;
import com.clearance.management.backend.entity.Offices;

import java.util.List;

public interface OfficeService {

    public List<OfficeDto> getOffices();
    public OfficeDto addOffice(OfficeDto request);
    public OfficeDto updateOffice(OfficeDto request);
    public OfficeDto updateOfficeAssignment(OfficeDto request);
    public void deleteOffice(Integer id);
}
