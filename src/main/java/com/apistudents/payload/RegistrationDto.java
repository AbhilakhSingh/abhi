package com.apistudents.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collector;

@Getter
@Setter

public class RegistrationDto {

    private Long id;

    @Size(min = 2, max = 20, message = "Should be 2 or more characters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 10, max = 15, message = "Should be 10 or more digits")
    private String mobile;

    private String message;

    private int pageNo;

}