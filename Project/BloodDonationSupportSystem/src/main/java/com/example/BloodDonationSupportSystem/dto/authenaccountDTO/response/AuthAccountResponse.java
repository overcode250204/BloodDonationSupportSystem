package com.example.BloodDonationSupportSystem.dto.authenaccountDTO.response;

import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.UserProfileDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthAccountResponse {
    @NotNull
    private String token;
}
