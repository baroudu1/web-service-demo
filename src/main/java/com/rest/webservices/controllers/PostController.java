package com.rest.webservices.controllers;


import com.rest.webservices.exceptionHandler.PostNotFound;
import com.rest.webservices.exceptionHandler.UserNotFound;
import com.rest.webservices.models.Post;
import com.rest.webservices.models.PostList;
import com.rest.webservices.models.User;
import com.rest.webservices.repositories.PostRepository;
import com.rest.webservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{idUser}/posts")
    public PostList getAllPosts(@PathVariable Long idUser) {

        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFound("User with id " + idUser + " not found"));

        PostList postList = new PostList();
        for (Post post : user.getPosts()) {
            Link link = linkTo(methodOn(this.getClass())
                    .getPost(idUser, post.getId()))
                    .withSelfRel();
            post.add(link);
            postList.getPosts().add(post);
        }
        Link selfLink =
                linkTo(methodOn(this.getClass()).getAllPosts(idUser))
                        .withSelfRel();
        postList.add(selfLink);

        return postList;
    }

    @GetMapping("/{idUser}/posts/{idPost}")
    public Post getPost(@PathVariable Long idUser, @PathVariable Long idPost) {

        if (!userRepository.existsById(idUser)) {
            throw new UserNotFound("User with id " + idUser + " not found");
        }
        Post post = postRepository.findById(idPost)
                .orElseThrow(() -> new PostNotFound("Post with id " + idPost + " not found"));
        Link link = linkTo(methodOn(this.getClass()).getAllPosts(idUser))
                .withRel("all_posts");
        post.add(link);
        return post;
    }
    @PostMapping("/{idUser}/posts")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Post createPost(@PathVariable Long idUser, @Valid @RequestBody Post post) {

        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFound("User with id " + idUser + " not found"));
        post.setUser(user);
        return postRepository.save(post);
    }

    @PutMapping("/{idUser}/posts/{idPost}")
    public Post updatePost(@PathVariable Long idUser, @PathVariable Long idPost, @RequestBody Post post) {

        if (!userRepository.existsById(idUser)) {
            throw new UserNotFound("User with id " + idUser + " not found");
        }
        Post post1 = postRepository.findById(idPost)
                .orElseThrow(() -> new PostNotFound("Post with id " + idPost + " not found"));
        post1.setBody(post.getBody());
        return postRepository.save(post1);
    }

    @DeleteMapping("/{idUser}/posts/{idPost}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long idUser, @PathVariable Long idPost) {

        if (!userRepository.existsById(idUser)) {
            throw new UserNotFound("User with id " + idUser + " not found");
        }
        if (!postRepository.existsById(idPost)) {
            throw new PostNotFound("Post with id " + idPost + " not found");
        }
        postRepository.deleteById(idPost);
    }

}
