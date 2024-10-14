package tech_5dhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userId;
    private String nameFromGoogle;
    private String fullNameFromGoogle;
    private String emailFromGoogle;
    private String email;
    private String role;
    private String password;
    private String provider;
}
