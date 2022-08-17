package com.rest.webservices.repositories;

import com.rest.webservices.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    boolean existsByEmail(String email);

    boolean existsById(Long id);
}
