package com.example.BloodDonationSupportSystem.entity;

import com.example.BloodDonationSupportSystem.enumentity.BloodTypeEnum;
import com.example.BloodDonationSupportSystem.enumentity.GenderEnum;
import com.example.BloodDonationSupportSystem.enumentity.StatusUserEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name ="user_table")
public class UserEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "user_id", columnDefinition = "uniqueidentifier")
    private UUID user_id;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "bloodType")
    @Enumerated(EnumType.STRING)
    private BloodTypeEnum bloodType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusUserEnum status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().getRoleName().name()));
    }


}
