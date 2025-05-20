package com.example.BloodDonationSupportSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity(name = "user")
@Data
public class UserEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "uniqueidentifier")
    private UUID user_id;




}
