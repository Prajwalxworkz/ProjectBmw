package com.xworkz.website.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bike_to_branch", uniqueConstraints = @UniqueConstraint(columnNames = "bike_id"))
@NamedQuery(name="findBikeToBranchById", query = "select entity from BikeToBranchEntity entity where entity.bike.id=:id")
@NamedQuery(name="getBikeCountByBranchId", query = "select count(entity) from BikeToBranchEntity entity where entity.branch.id=:id")
@NamedQuery(name="getBikeToBranchByModelName", query = "select entity from BikeToBranchEntity entity where entity.bike.modelName=:name")
public class BikeToBranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private BranchEntity branch;

    @OneToOne
    @JoinColumn(name = "bike_id", nullable = false, unique = true)
    private BikeEntity bike;
}

