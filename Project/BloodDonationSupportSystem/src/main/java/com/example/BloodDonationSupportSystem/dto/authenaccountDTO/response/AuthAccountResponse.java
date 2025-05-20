package com.example.BloodDonationSupportSystem.dto.authenaccountDTO.response;

import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.UserProfileDTO;
import lombok.Data;

@Data
public class AuthAccountResponse {

    private String token;

    private UserProfileDTO user;

}
