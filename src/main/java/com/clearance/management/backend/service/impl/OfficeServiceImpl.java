package com.clearance.management.backend.service.impl;

import com.clearance.management.backend.dto.OfficeDto;
import com.clearance.management.backend.entity.Offices;
import com.clearance.management.backend.exception.ResourceNotFoundException;
import com.clearance.management.backend.repository.OfficeRepository;
import com.clearance.management.backend.service.OfficeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OfficeDto> getOffices() {
        List<Offices> offices = officeRepository.findAll();
        return offices
                .stream()
                .map((office) -> modelMapper.map(office, OfficeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OfficeDto addOffice(OfficeDto request) {
        Offices office = modelMapper.map(request, Offices.class);
        Offices addedOffice = officeRepository.save(office);
        return modelMapper.map(addedOffice, OfficeDto.class);
    }

    @Override
    public OfficeDto updateOffice(OfficeDto request) {
        Offices office = officeRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Office with ID " + request.getId() + " not found."));
        office.setName(request.getName());
        Offices savedOffice = officeRepository.save(office);
        return modelMapper.map(savedOffice, OfficeDto.class);
    }

    @Override
    public OfficeDto updateOfficeAssignment(OfficeDto request) {
        Offices office = officeRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Office with ID " + request.getId() + " not found."));
        office.setIsOfficeHeadAssigned(request.getIsOfficeHeadAssigned());
        Offices savedOffice = officeRepository.save(office);
        return modelMapper.map(savedOffice, OfficeDto.class);
    }

    public void deleteOffice(Integer id) {
        Offices office = officeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Office with ID " + id + " not found."));
        officeRepository.delete(office);
    }
}
