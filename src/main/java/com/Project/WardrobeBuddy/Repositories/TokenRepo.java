package com.Project.WardrobeBuddy.Repositories;

import com.Project.WardrobeBuddy.Models.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<Tokens,String> {
}
