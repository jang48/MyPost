package com.korea.test;

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
    // 데이터베이스에 데이터가 없는 경우, 초기 데이터 생성 및 저장
    if (this.postRepository.count() == 0) {
      Post post = new Post();
      post.setTitle("new title..");
      post.setContent("");
      post.setCreateDate(LocalDateTime.now());
      postRepository.save(post);
    }
  }
}
