package com.xworkz.website.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name="follow_up")
@NamedQuery(name="getFollowUpDetailsByCustomerId", query = "select entity from FollowUpEntity entity where entity.customerEntity.id=:id")
public class FollowUpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;
    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customerEntity;
}
