package com.korea.test.commend;

import com.korea.test.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommendRepository extends JpaRepository<Commend, Integer> {
  List<Commend> findByPostId(Integer Id);

}
