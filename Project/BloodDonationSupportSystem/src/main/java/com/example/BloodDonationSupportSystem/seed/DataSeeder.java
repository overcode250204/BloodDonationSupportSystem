package com.example.BloodDonationSupportSystem.seed;


import com.example.BloodDonationSupportSystem.entity.RoleEntity;
import com.example.BloodDonationSupportSystem.enumentity.RoleEnum;
import com.example.BloodDonationSupportSystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        List<RoleEnum> defaultRoles = List.of(RoleEnum.ROLE_GUEST, RoleEnum.ROLE_MEMBER, RoleEnum.ROLE_STAFF, RoleEnum.ROLE_ADMIN);
        defaultRoles.forEach(role -> {
            if (roleRepository.findByRoleName(role).isEmpty()) {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setRoleName(role);
                roleRepository.save(roleEntity);
            }
        });

    }
}
