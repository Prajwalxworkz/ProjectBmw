package com.xworkz.website.dto;

import com.xworkz.website.admin.constants.Schedule;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CustomerDto {

    private Long id;
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z ]{3,20}$", message = "Name must be at least 3 characters and contain only letters and spaces")
    private String fullName;
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9_.%+-]+@gmail\\.com$", message = "domain should be gmail.com, no spaces in between and only '_', '.', '%', '+', and '-' are allowed.")
    private String email;
    @NotNull(message = "Date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dto;
    @NotBlank(message = "Address is required")
    private String address;
    private Long phoneNumber;
    @NotNull(message = "DL is required")
    private String drivingLicense;
    private Schedule schedule;
    @NotNull(message = "Date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateAndTime;
    private String modelName;
    private String branchName;
    private Long bikeId;
    private String password;
    private String confirmPassword;
    private String profilePicture;
    private MultipartFile multipartFile;

}
