package com.korea.test.subcategory;

import com.korea.test.maincategory.MainCategory;
import com.korea.test.maincategory.MainCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/category")
@RequiredArgsConstructor
@Controller
public class SubCategoryController {

  private final MainCategoryRepository mainCategoryRepository;
  private final SubCategoryService subCategoryService;

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/subadd/{id}")
  public String add(Model model, @PathVariable("id") Integer id ){
    MainCategory mainCategory = this.mainCategoryRepository.findById(id).get();
    this.subCategoryService.create(mainCategory);
    return  "redirect:/home";
  }

}
