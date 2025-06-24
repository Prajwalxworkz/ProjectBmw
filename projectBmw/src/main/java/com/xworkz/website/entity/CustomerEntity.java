package com.xworkz.website.entity;

import com.xworkz.website.admin.constants.Schedule;
import com.xworkz.website.dto.BikeToBranchDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter@ToString
@Entity
@Table(name = "customer")
@NamedQuery(name="getCustomerDetails", query = "select customer from CustomerEntity customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    private LocalDate dto;
    private String address;
    private Long phoneNumber;
    private String drivingLicense;
    @Enumerated(EnumType.STRING)
    private Schedule schedule;
    @Column(name = "date_time")
    private LocalDateTime dateAndTime;
    @ManyToOne
    @JoinColumn(name = "bike_to_branch_id", nullable = false)
    private BikeToBranchEntity bikeToBranch;

}
