package com.example.BloodDonationSupportSystem.dto.authenaccountDTO.response;

import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.UserProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthAccountResponse {
    private String token;
    private UserProfileDTO user;


}
