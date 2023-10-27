package com.korea.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<com.korea.test.Post, Long>  {

}