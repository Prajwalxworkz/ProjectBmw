package com.xworkz.website.entity;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@ToString
@Entity
@Table(name = "bike")
@NamedQuery(name="getAllBikes", query = "select bike from BikeEntity bike")
@NamedQuery(name="findBikeById", query = "select bike from BikeEntity bike where bike.id =:id")
public class BikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="model_name")
    private String modelName;
    @Column(name="top_speed")
    private Double topSpeed;
    private String transmission;
    private Double mileage;
    @Column(name="engine_capacity")
    private Double engineCapacity;
    @Column(name="kerb_weight")
    private Double kerbWeight;
    @Column(name="fuel_tank_capacity")
    private Double fuelTankCapacity;
    @Column(name="ex_showroom_price")
    private Double exShowroomPrice;
    @Column(name="front_view")
    private String frontView;
    @Column(name="rear_view")
    private String rearView;
    @Column(name="side_view1")
    private String sideView1;
    @Column(name="side_view2")
    private String sideView2;
}
