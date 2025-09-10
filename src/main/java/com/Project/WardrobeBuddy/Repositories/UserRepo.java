package com.Project.WardrobeBuddy.Repositories;

import com.Project.WardrobeBuddy.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    Optional<User> findByUsernameAndPassword(String username, String password);
}
