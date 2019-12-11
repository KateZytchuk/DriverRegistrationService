package driveup.registration.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    @NotNull
    private long userId;

    @Column(name = "phone", nullable = false, length = 13)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @Column(name = "email", length = 30)
    private String email = "";

    @Column(name = "user_name", length = 25)
    private String userName = "";

    @Column(name = "user_second_name", length = 25)
    private String secondName = "";

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    @Column(columnDefinition="text")
    private List<Role> roles;

    public User(@JsonProperty("email") String email,
                @JsonProperty("phone") String phone,
                @JsonProperty("password") String password,
                @JsonProperty("user_second_name") String secondName,
                @JsonProperty("user_name") String userName) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.secondName = secondName;
        this.userName = userName;
    }
}
