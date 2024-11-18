package tech_5dhub.dto;

import com.google.api.services.calendar.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<String> events;
}
