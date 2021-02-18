package com.india.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.india.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
