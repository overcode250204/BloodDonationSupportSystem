package com.example.BloodDonationSupportSystem.dto.authenaccountDTO;

import lombok.Data;

import java.util.Date;

@Data
public class UserProfileDTO {
    private String fullName;
    private Date dayOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String longitude;
    private String latitude;
    private String bloodType;
}
