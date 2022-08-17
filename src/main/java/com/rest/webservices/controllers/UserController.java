package com.rest.webservices.controllers;


import com.rest.webservices.exceptionHandler.UserAlreadyExists;
import com.rest.webservices.exceptionHandler.UserNotFound;
import com.rest.webservices.models.User;
import com.rest.webservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController @RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFound("User not found"));
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExists("User with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFound("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) {

        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFound("User not found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

}
