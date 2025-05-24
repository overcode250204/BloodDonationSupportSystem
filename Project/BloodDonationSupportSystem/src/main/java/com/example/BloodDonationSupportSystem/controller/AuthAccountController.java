package com.example.BloodDonationSupportSystem.controller;

import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.request.LoginRequest;
import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.request.RegisterRequest;
import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.response.AuthAccountResponse;
import com.example.BloodDonationSupportSystem.dto.common.BaseReponse;
import com.example.BloodDonationSupportSystem.service.authaccountservice.AuthAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
public class AuthAccountController {
    @Autowired
    private AuthAccountService authAccountService;

    @PostMapping("/register")
    public BaseReponse<?> register(@RequestBody RegisterRequest authAccountRequest) {
        AuthAccountResponse data = authAccountService.register(authAccountRequest);
        return new BaseReponse<>(HttpStatus.OK.value(), "Success", data);
    }


    @PostMapping("/login")
    public BaseReponse<?> login(@RequestBody LoginRequest loginRequest) {
        AuthAccountResponse data = authAccountService.authAccount(loginRequest);
        return new BaseReponse<>(HttpStatus.OK.value(), "Succes", data);
    }


}
