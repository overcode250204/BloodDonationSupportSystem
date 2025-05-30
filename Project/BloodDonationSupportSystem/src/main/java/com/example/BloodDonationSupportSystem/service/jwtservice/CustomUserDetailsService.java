package com.example.BloodDonationSupportSystem.service.jwtservice;

import com.example.BloodDonationSupportSystem.entity.UserEntity;
import com.example.BloodDonationSupportSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByPhoneNumber(userID).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                String.valueOf(user.getUser_id()),
                user.getPasswordHash(),
                user.getAuthorities()
        );
    }


}
