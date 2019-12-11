package driveup.registration.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

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
    private long userId;

    @Column(name = "phone", nullable = false, length = 13)
    private String phone;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Email
    @Column(name = "email", length = 30)
    private String email = "";

    @Column(name = "user_name", length = 25)
    private String userName = "";

    @Column(name = "customer_second_name", length = 25)
    private String secondName = "";

    public User(@JsonProperty("phone") String phone,
                @JsonProperty("password") String password) {
        this.phone = phone;
        this.password = password;
    }
}
