package driveup.registration.service.service;

import driveup.registration.service.exceptions.PhoneExistsException;
import driveup.registration.service.model.User;
import driveup.registration.service.repository.UserRepository;
import driveup.registration.service.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public User registerNewUserAccount(RegistrationRequest registrationRequest)
            throws PhoneExistsException {

        if (phoneExists(registrationRequest.getPhone())) {
            throw new PhoneExistsException(
                    "There is an account with that phone address:"
                            + registrationRequest.getPhone());
        }
        User user = new User();
        user.setUserName("");
        user.setSecondName("");
        user.setPassword(registrationRequest.getPassword());
        user.setEmail("");
        user.setRoles(Arrays.asList("ROLE_CUSTOMER"));
        return userRepository.save(user);
    }

    private boolean phoneExists(String phone) {
        User user = userRepository.findByPhone(phone);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User updatePassword(long customerId, String oldPassword, String newPassword) {
        User user = getCustomerById(customerId);
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
        }
        return userRepository.save(user);
    }

    @Override
    public User updateCustomer(long customerId, String email, String firstName, String secondName) {
        User customer = getCustomerById(customerId);
        customer.setEmail(email);
        customer.setUserName(firstName);
        customer.setSecondName(secondName);
        return userRepository.save(customer);
    }

    @Override
    public User getCustomerById(long customerId) {
        return userRepository.findById(customerId).get();
    }

    @Override
    public String getEmailById(long customerId) {
        return userRepository.findById(customerId).get().getEmail();
    }

    @Override
    public String getPhoneById(long customerId) {
        return userRepository.findById(customerId).get().getPhone();
    }

    @Override
    public Iterable<User> getAllCustomers() {
        return userRepository.findAll();
    }
}
