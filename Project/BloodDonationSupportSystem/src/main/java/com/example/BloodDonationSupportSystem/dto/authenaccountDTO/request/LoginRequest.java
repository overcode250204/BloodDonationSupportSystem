package com.example.BloodDonationSupportSystem.dto.authenaccountDTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequest {
    @NotNull
    @Length(max = 10)
    private String phoneNumber;
    @NotNull
    @Length(max = 10)
    private String password;
}
