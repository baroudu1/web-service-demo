package com.rest.webservices.controllers;


import com.rest.webservices.exceptionHandler.UserAlreadyExists;
import com.rest.webservices.exceptionHandler.UserNotFound;
import com.rest.webservices.models.User;
import com.rest.webservices.models.UsersList;
import com.rest.webservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController @RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public UsersList getUsers() {
        UsersList usersList = new UsersList();
        for (User user : userRepository.findAll()) {

            Link link = linkTo(this.getClass())
                    .slash(user.getId()).withSelfRel();

            user.add(link);
            usersList.getUsers().add(user);
        }
        Link selfLink =
                linkTo(methodOn(this.getClass()).getUsers())
                        .withSelfRel();

        usersList.add(selfLink);

        return usersList;
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User with id " + id + " not found"));
        Link link = linkTo(methodOn(this.getClass()).getUsers())
                .withRel("all_users");
        user.add(link);
        return user;
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
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User with id " + id + " not found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

}
