package tech_5dhub.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.List;

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
    @ElementCollection
    @ToStringExclude
    @CollectionTable(name = "user_events",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<String> event;
}
