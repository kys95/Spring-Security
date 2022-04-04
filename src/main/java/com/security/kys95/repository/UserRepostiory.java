package com.security.kys95.repository;

import com.security.kys95.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepostiory extends JpaRepository<User, Integer> {
}
