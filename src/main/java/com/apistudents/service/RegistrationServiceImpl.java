package com.apistudents.service;

import com.apistudents.entity.Registration;
import com.apistudents.payload.RegistrationDto;
import com.apistudents.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService{
    @Autowired
    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public RegistrationServiceImpl(){

    }

    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(savedEntity);
        dto.setMessage("Registration saved");
        return dto;
    }

    @Override
    public RegistrationDto deleteRegistrationById(long id) {
        registrationRepository.deleteById(id);
        return null;
    }

    @Override
    public RegistrationDto updateRegistration(long id, RegistrationDto registrationDto) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        Registration registration = opReg.get();

        registration.setName(registration.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());
        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(registration);
        return dto;
    }

    @Override
    public List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
        //List<Registration> registrations = registrationRepository.findAll();
        //Ternary Operator

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Registration> all = registrationRepository.findAll(pageable);
        List<Registration> registrations = all.getContent();
        List<RegistrationDto> registrationsDtos = registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        System.out.println(all.getTotalPages());
        System.out.println(all.isLast());
        System.out.println(all.isFirst());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        return registrationsDtos;
    }

    @Override
    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new ResolutionException("Registration not found with id: " + id)
        );

        RegistrationDto registrationDto = mapToDto(registration);
        return registrationDto;
    }

        Registration mapToEntity(RegistrationDto dto){
        Registration entity = new Registration();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        return entity;
    }

    static RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto = new RegistrationDto();
        dto.setName(dto.getName());
        dto.setEmail(dto.getEmail());
        dto.setMobile(dto.getMobile());
        return dto;
    }
}
