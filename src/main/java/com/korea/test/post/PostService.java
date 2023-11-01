package com.korea.test.post;

import com.korea.test.maincategory.MainCategory;
import com.korea.test.subcategory.SubCategory;
import com.korea.test.subcategory.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final SubCategoryRepository subCategoryRepository;

  public void findsubid(int id){
  }
  public void createpost(SubCategory subCategory){
    Post post = new Post();
    post.setSubCategory(subCategory);
    post.setTitle("new title");
    post.setContent("");
    post.setCreateDate(LocalDateTime.now());
    this.postRepository.save(post);
  }

  public Page<Post> getList(int page) {
    Pageable pageable = PageRequest.of(page, 18);
    return this.postRepository.findAll(pageable);
  }

}