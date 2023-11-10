package com.korea.test.post;

import com.korea.test.maincategory.MainCategory;
import com.korea.test.subcategory.SubCategory;
import com.korea.test.subcategory.SubCategoryRepository;
import com.korea.test.user.SiteUser;
import com.korea.test.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final SubCategoryRepository subCategoryRepository;
  private final UserRepository userRepository;

  public void findsubid(int id){
  }
  public void createpost(SubCategory subCategory, String postname){
    Post post = new Post();
    post.setSubCategory(subCategory);
    post.setTitle("new title");
    post.setContent("");
    post.setCreateDate(LocalDateTime.now());
    SiteUser siteUser = userRepository.findByusername(postname).get();
    post.setUser(siteUser);
    this.postRepository.save(post);
  }

  public Page<Post> getList(int page, Integer subCategoryId) {
    List<Sort.Order> sorts = new ArrayList<>();
    sorts.add(Sort.Order.desc("createDate"));
    Pageable pageable  = PageRequest.of(page, 12,Sort.by(sorts));
    return this.postRepository.findBySubCategoryId(subCategoryId, pageable);
  }

  public void updatepost(Integer id, String content){
    Post post = this.postRepository.findById(id.longValue()).get();
    post.setContent(content);
    this.postRepository.save(post);
  }

}
