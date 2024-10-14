package tech_5dhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistration {

    @NotBlank(message = "The user's first name is not specified")
    private String nameFromGoogle;
    @NotBlank(message = "The user's last name is not specified")
    private String fullNameFromGoogle;
    private String emailFromGoogle;
    @Email
    @NotBlank(message = "Email is not specified")
    private String email;
    private String role;
    @Size(min = 8, max = 256, message = "The email length should be in the range from 8 to 256 characters")
    private String password;
    private String provider;
}
