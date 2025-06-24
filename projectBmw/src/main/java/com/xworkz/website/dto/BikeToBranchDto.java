package com.xworkz.website.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BikeToBranchDto {

    private Long id;
    private Long branchId;
    private Long bikeId;

}
