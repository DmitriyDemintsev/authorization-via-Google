package tech_5dhub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name_from_google")
    private String nameFromGoogle;
    @Column(name = "full_name_from_google")
    private String fullNameFromGoogle;
    @Column(name = "email_from_google")
    private String emailFromGoogle;
    private String email;
    private String role;
    private String password;
    private String provider;
}
