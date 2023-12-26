package com.agridence.microservice.Assignment.Repository;

import com.agridence.microservice.Assignment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findById(Long userId);

    Boolean existsByUsername(String userName);

    User save(User user);

    List<User> findAll();

    Optional<User> findByUsername(String userName);
}
