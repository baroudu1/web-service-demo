package com.rest.webservices.models;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;

@Getter @Setter @NoArgsConstructor @ToString
public class PostList extends RepresentationModel<PostList> {
    private ArrayList<Post> posts = new ArrayList<>();
}
