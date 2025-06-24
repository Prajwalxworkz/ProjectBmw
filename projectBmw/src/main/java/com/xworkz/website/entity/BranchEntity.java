package com.xworkz.website.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "branch")
@NamedQuery(name="getAllBranches", query = "select branch from BranchEntity branch")
@NamedQuery(name="findBranchById", query = "select branch from BranchEntity branch where branch.id=:id")

public class BranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "branch_name")
    private String branchName;
    private String location;
    private String city;
    private Long pinCode;
    @Column(name = "branch_picture")
    private String branchPicture;
}
