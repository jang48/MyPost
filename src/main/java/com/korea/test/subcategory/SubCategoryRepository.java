package com.korea.test.subcategory;

import com.korea.test.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer> {
  List<SubCategory> findByMainCategoryId(Integer mainCategoryId);

}
