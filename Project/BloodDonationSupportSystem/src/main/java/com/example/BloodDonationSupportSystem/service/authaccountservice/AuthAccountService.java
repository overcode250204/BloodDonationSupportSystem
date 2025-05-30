package com.example.BloodDonationSupportSystem.service.authaccountservice;

import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.UserProfileDTO;
import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.request.LoginRequest;
import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.request.RegisterRequest;
import com.example.BloodDonationSupportSystem.dto.authenaccountDTO.response.AuthAccountResponse;
import com.example.BloodDonationSupportSystem.entity.RoleEntity;
import com.example.BloodDonationSupportSystem.entity.UserEntity;
import com.example.BloodDonationSupportSystem.enumentity.RoleEnum;
import com.example.BloodDonationSupportSystem.enumentity.StatusUserEnum;
import com.example.BloodDonationSupportSystem.repository.RoleRepository;
import com.example.BloodDonationSupportSystem.repository.UserRepository;
import com.example.BloodDonationSupportSystem.service.jwtservice.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthAccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthAccountResponse register(RegisterRequest registerRequest) {
        AuthAccountResponse authAccountResponse;
        UserEntity user = new UserEntity();
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        Optional<RoleEntity> roleMember = roleRepository.findByRoleName(RoleEnum.ROLE_MEMBER);
        user.setRole(roleMember.orElseThrow());
        user.setFullName(registerRequest.getFullName());
        user.setAddress(registerRequest.getAddress());
        user.setDateOfBirth(registerRequest.getDateOfBirth());
        user.setGender(registerRequest.getGender());
        user.setStatus(StatusUserEnum.ACTIVE);
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);



        String token = jwtService.generateToken(
                new User(
                        String.valueOf(user.getUser_id()),
                        user.getPasswordHash(),
                        user.getAuthorities()
                )
        );
        authAccountResponse = new AuthAccountResponse(token);
        return authAccountResponse;
    }

    public AuthAccountResponse authAccount(LoginRequest loginRequest) {
        AuthAccountResponse authAccountResponse;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword()));
        UserEntity user = userRepository.findByPhoneNumber(loginRequest.getPhoneNumber()).orElseThrow();

        String token = jwtService.generateToken(new User(String.valueOf(user.getUser_id()), user.getPasswordHash(), Collections.singleton(new SimpleGrantedAuthority(user.getRole().getRoleName().name()))));
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setPhoneNumber(user.getPhoneNumber());
        userProfileDTO.setGender(user.getGender());
        userProfileDTO.setAddress(user.getAddress());
        userProfileDTO.setLatitude(user.getLatitude());
        userProfileDTO.setLongitude(user.getLongitude());
        userProfileDTO.setBloodType(user.getBloodType());
        userProfileDTO.setDayOfBirth(user.getDateOfBirth());
        authAccountResponse = new AuthAccountResponse(token);
        return authAccountResponse;


    }

    public UserEntity createUser(UserEntity newUser) {
        return userRepository.save(newUser);
    }




}
