package com.example.BloodDonationSupportSystem.dto.authenaccountDTO;


import com.example.BloodDonationSupportSystem.enumentity.BloodTypeEnum;
import com.example.BloodDonationSupportSystem.enumentity.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private String fullName;
    private LocalDate dayOfBirth;
    private GenderEnum gender;
    private String address;
    private String phoneNumber;
    private String longitude;
    private String latitude;
    private BloodTypeEnum bloodType;

}
