package com.latif.userservice.repository;

import com.latif.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getDistinctTopByUsername(String username);
}
