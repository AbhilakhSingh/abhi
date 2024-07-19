package com.apistudents.service;

import com.apistudents.payload.RegistrationDto;

import java.util.List;

public interface RegistrationService {

    public RegistrationDto createRegistration(RegistrationDto registrationDto);

    RegistrationDto deleteRegistrationById(long id);

    RegistrationDto updateRegistration(long id, RegistrationDto registrationDto);

    List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir);

    RegistrationDto getRegistrationById(long id);
}
