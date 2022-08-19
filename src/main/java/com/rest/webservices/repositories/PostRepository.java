package com.rest.webservices.repositories;

import com.rest.webservices.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    @Query(value = "SELECT * FROM posts WHERE user_id = ?1", nativeQuery = true)
//    Optional<List<Post>> findAllByUserId(Long id);

}
