package com.example.BloodDonationSupportSystem.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseReponse<T> {
    private int status;
    private String message;
    private T data;
}
