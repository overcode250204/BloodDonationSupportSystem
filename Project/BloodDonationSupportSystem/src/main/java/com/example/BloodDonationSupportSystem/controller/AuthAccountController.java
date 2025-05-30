package com.example.BloodDonationSupportSystem.controller;

import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.request.LoginRequest;
import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.request.RegisterRequest;
import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.response.AuthAccountResponse;
import com.example.BloodDonationSupportSystem.dto.common.BaseReponse;
import com.example.BloodDonationSupportSystem.entity.OauthAccountEntity;
import com.example.BloodDonationSupportSystem.entity.UserEntity;
import com.example.BloodDonationSupportSystem.service.authaccountservice.AuthAccountService;
import com.example.BloodDonationSupportSystem.service.authaccountservice.GoogleOAuthService;
import com.example.BloodDonationSupportSystem.service.authaccountservice.OauthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
@CrossOrigin()
public class AuthAccountController {

    private static final String GOOGLE_PROVIDER = "google";

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.redirect.uri}")
    private String redirectUri;



    @Autowired
    private OauthService oauthService;
    @Autowired
    private AuthAccountService authAccountService;
    @Autowired
    private GoogleOAuthService googleOAuthService;

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

    @GetMapping("/login/google")
    public void redirectToGoogle(HttpServletResponse response) {
     //   log.info("Redirecting to Google OAuth");
        googleOAuthService.redirectToGoogle(response);
    }

    @GetMapping("/callback/google")
    public BaseReponse<?> handleGoogleCallback(@RequestParam("code") String code) {
        try {

            return googleOAuthService.handleGoogleCallback(code);
        } catch (Exception e) {
            return new BaseReponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error during Google OAuth callback: " + e.getMessage(),
                    null
            );
        }
    }

}
