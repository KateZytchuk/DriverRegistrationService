package driveup.registration.service.controller;

import driveup.registration.service.model.User;
import driveup.registration.service.request.RegistrationRequest;
import driveup.registration.service.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

//    @PostMapping("/registration")
//    public ResponseEntity<User> registration(@RequestBody RegistrationRequest registrationRequest) {
//
//    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody RegistrationRequest registrationRequest) {
        User user = userService.saveUser(registrationRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<User> changePassword(@PathVariable long customerId,
                                               @PathVariable String oldPassword,
                                               @PathVariable String newPassword) {
        User customer = userService.updatePassword(customerId, oldPassword, newPassword);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<User> updateCustomer(@PathVariable long customerId, String email,
                                               String firstName, String secondName) {
        User customer = userService.updateCustomer(customerId, email, firstName, secondName);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/get_customer_by_id")
    public ResponseEntity<User> getCustomerById(@PathVariable long customerId) {
        User customer = userService.getCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/get_email_by_id")
    public ResponseEntity<String> getEmailById(@PathVariable long customerId) {
        String email = userService.getEmailById(customerId);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @GetMapping("/get_phone_by_id")
    public ResponseEntity<String> getPhoneById(@PathVariable long customerId) {
        String phone = userService.getPhoneById(customerId);
        return new ResponseEntity<>(phone, HttpStatus.OK);
    }

    @GetMapping("/get_all")
    public ResponseEntity getAll() {
        Iterable listOfCustomers = userService.getAllCustomers();
        return new ResponseEntity<>(listOfCustomers, HttpStatus.OK);
    }
}
