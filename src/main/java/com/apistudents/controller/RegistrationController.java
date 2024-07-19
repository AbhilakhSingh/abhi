package com.apistudents.controller;

import com.apistudents.entity.Registration;
import com.apistudents.payload.RegistrationDto;
import com.apistudents.repository.RegistrationRepository;
import com.apistudents.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/v1/registration")
    public class RegistrationController {

    private RegistrationService registrationService;
    private final RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationService registrationService,
                                  RegistrationRepository registrationRepository) {
        this.registrationService = registrationService;
        this.registrationRepository = registrationRepository;
    }

    //http://localhost:8080/api/v1/registration

        @PostMapping
        public ResponseEntity<?> addRegistration(
                @Valid @RequestBody RegistrationDto registrationDto,
                BindingResult result) {
            if(result.hasErrors()) {
                return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
            }
            RegistrationDto regDto = registrationService.createRegistration(registrationDto);
            return new ResponseEntity<>(regDto, HttpStatus.CREATED);
        }

        //http://localhost:8080/api/v1/registration?id=

        @DeleteMapping
        public ResponseEntity<String> deleteRegistrationById(
                @RequestParam long id)
        {
            registrationService.deleteRegistrationById(id);
            return new ResponseEntity<>("Registration deleted", HttpStatus.OK);
        }

        @PutMapping
        public ResponseEntity<RegistrationDto> updateRegistration(
                @RequestParam long id,
                @RequestBody RegistrationDto registrationDto
        ){
            RegistrationDto dto = registrationService.updateRegistration(id, registrationDto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }

        //http://localhost:8080/api/v1/registration?pageNp=1&pageSize=5&sortBy=email&sortDir=asc
        @GetMapping
        public ResponseEntity<List<RegistrationDto>> getAllRegistrations(
                @RequestParam(name="pageNo", defaultValue = "0", required = false) int pageNo,
                @RequestParam(name="pageSize", defaultValue = "5", required = false) int pageSize,
                @RequestParam(name="sortBy", defaultValue = "name", required = false) String sortBy,
                @RequestParam(name="sortDir", defaultValue = "asc", required = false) String sortDir
        ){
            List<RegistrationDto> dtos = registrationService.getAllRegistrations(pageNo, pageSize, sortBy, sortDir);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
@GetMapping("/byid")
    public ResponseEntity<RegistrationDto> getRegistrationById(
            @RequestParam long id
   ){
        RegistrationDto dto = registrationService.deleteRegistrationById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
