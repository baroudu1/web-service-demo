package com.rest.webservices.models;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;

@Getter @Setter @NoArgsConstructor @ToString
public class UsersList extends RepresentationModel<UsersList> {
    private ArrayList<User> users = new ArrayList<>();
}
