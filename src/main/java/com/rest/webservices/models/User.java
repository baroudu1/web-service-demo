package com.rest.webservices.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @ToString
public class User extends RepresentationModel<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 20 , message = "Name should be between 3 and 20 characters")
    private String name;

    @Email(message = "Email should be valid")
    private String email;


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

}

