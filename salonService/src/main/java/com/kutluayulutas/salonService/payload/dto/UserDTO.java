package com.kutluayulutas.salonService.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {

    private Long id;

    private String fullName;

    private String email;
}
