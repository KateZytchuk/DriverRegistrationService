package driveup.registration.service.component;

import driveup.registration.service.model.Role;
import driveup.registration.service.model.User;
import driveup.registration.service.repository.RoleRepository;
import driveup.registration.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        createRoleIfNotFound("ROLE_DRIVER");
        createRoleIfNotFound("ROLE_CUSTOMER");

        Role customerRole = roleRepository.findByName("ROLE_CUSTOMER");

        User user = new User();
        user.setEmail("test11@test.com");
        user.setPhone("+380951683807");
        user.setPassword(passwordEncoder.encode("test7"));
        user.setSecondName("Test2");
        user.setUserName("Test3");
        user.setRoles(Arrays.asList(customerRole));
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    protected Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}
