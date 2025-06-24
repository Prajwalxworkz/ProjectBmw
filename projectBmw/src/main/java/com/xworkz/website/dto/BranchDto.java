package com.xworkz.website.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString

public class BranchDto {

    private Long id;
    private String branchName;
    private String location;
    private String city;
    private String pinCode;
    private String branchPicture;
    private MultipartFile multipartFile;
}
