package com.xworkz.website.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BikeDto {

    private Long id;
    private String modelName;
    private Double topSpeed;
    private String transmission;
    private Double mileage;
    private Double engineCapacity;
    private Double kerbWeight;
    private Double fuelTankCapacity;
    private Double exShowroomPrice;
    private String frontView;
    private MultipartFile multipartFile1;
    private String rearView;
    private MultipartFile multipartFile2;
    private String sideView1;
    private MultipartFile multipartFile3;
    private String sideView2;
    private MultipartFile multipartFile4;
}
