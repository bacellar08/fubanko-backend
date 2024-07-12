package io.github.fubanko.fubanko.repository;

import io.github.fubanko.fubanko.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUsername(String username);
}
