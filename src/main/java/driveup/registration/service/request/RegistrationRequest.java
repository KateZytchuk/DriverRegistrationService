package driveup.registration.service.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {
    @NotEmpty
    private String phone;
    @NotEmpty
    private String password;
}
