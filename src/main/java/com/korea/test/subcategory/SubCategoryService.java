package com.korea.test.subcategory;

import com.korea.test.maincategory.MainCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubCategoryService {
  private final SubCategoryRepository subCategoryRepository;

  public void create(MainCategory mainCategory){
    SubCategory subCategory = new SubCategory();
    subCategory.setSubtitle("제목없음");
    subCategory.setMainCategory(mainCategory);
    this.subCategoryRepository.save(subCategory);
  }

  public  List<SubCategory> detail(Integer id){
    List<SubCategory> subCategories;
    subCategories = this.subCategoryRepository.findByMainCategoryId(id);
    return subCategories;
  }
}
