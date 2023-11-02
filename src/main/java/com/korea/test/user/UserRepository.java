package com.korea.test.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Integer> {
  Optional<SiteUser> findByusername(String email);
}
