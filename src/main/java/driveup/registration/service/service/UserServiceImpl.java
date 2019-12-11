package driveup.registration.service.service;

import driveup.registration.service.model.User;
import driveup.registration.service.repository.UserRepository;
import driveup.registration.service.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User addUserToDb(User user) {
        return userRepository.save(user);
    }
    @Override
    public User saveUser(RegistrationRequest registrationRequest) {
        User user = new User(registrationRequest.getPhone(), registrationRequest.getPassword());
        return userRepository.save(user);
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
