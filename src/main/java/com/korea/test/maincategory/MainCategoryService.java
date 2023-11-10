package com.korea.test.maincategory;

import com.korea.test.subcategory.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MainCategoryService {
  private final MainCategoryRepository mainCategoryRepository;
  public void addmain(List<SubCategory> subCategory){
    MainCategory mainCategory = new MainCategory();
    mainCategory.setMaintitle("제목없음");
    mainCategory.setSubCategories(subCategory);
    mainCategory.setCreateDatetime(LocalDateTime.now());
    this.mainCategoryRepository.save(mainCategory);
  }

  public void deletemain(Integer mainid){
    MainCategory mainCategory = this.mainCategoryRepository.findById(mainid).get();
    this.mainCategoryRepository.delete(mainCategory);
  }
}
