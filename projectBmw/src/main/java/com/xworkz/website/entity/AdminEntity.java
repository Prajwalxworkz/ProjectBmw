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
@Table(name = "admin")
@NamedQuery(name="getAdminDetails", query = "select admin from AdminEntity admin where admin.email=:email")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    @Column (name="phone_number")
    private Long phoneNumber;
    private Integer otp;
    @Column (name="generated_at")
    private LocalDateTime generatedAt;
    @Column (name="expires_at")
    private LocalDateTime expiresAt;

}
