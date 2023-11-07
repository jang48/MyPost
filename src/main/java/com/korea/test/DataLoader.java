package com.korea.test;

import com.korea.test.post.Post;
import com.korea.test.post.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {
  private final PostRepository postRepository; // Post 엔터티를 저장할 Repository

  public DataLoader(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    if (this.postRepository.count() == 0) {
      Post post = new Post();
      post.setTitle("new title..");
      post.setContent("");
      post.setCreateDate(LocalDateTime.now());
      postRepository.save(post);
    }
  }
}
