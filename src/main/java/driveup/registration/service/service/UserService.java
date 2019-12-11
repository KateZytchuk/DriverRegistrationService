package driveup.registration.service.service;

import driveup.registration.service.exceptions.PhoneExistsException;
import driveup.registration.service.model.User;
import driveup.registration.service.request.RegistrationRequest;

public interface UserService {

    public User registerNewUserAccount(RegistrationRequest registrationRequest)
            throws PhoneExistsException;

    User updatePassword(long customerId, String oldPassword, String newPassword);

    User updateCustomer(long customerId, String email, String userName,
                            String secondName);

    User getCustomerById(long customerId);

    String getEmailById(long customerId);

    String getPhoneById(long customerId);

    Iterable<User> getAllCustomers();
}
