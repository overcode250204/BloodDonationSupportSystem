package com.example.BloodDonationSupportSystem.dto.authenaccountDTO.request;

import com.example.BloodDonationSupportSystem.enumentity.GenderEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    @NotNull
    @Length(max = 10)
    private String phoneNumber;
    @NotNull
    @Length(max = 10)
    private String password;
    @NotNull
    private String fullName;

    @NotNull
    private GenderEnum gender;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private String address;





}
