package com.korea.test.maincategory;

import com.korea.test.post.Post;
import com.korea.test.post.PostRepository;
import com.korea.test.subcategory.SubCategory;
import com.korea.test.subcategory.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SpinnerUI;
import java.util.List;
@Controller
public class MainCategoryController {
  @Autowired
  MainCategoryRepository mainCategoryRepository;
  @Autowired
  SubCategoryRepository subCategoryRepository;

  @Autowired
  PostRepository postRepository;


  @RequestMapping("/category")
  public String main2(Model model) {
    List<MainCategory> mainCategory = this.mainCategoryRepository.findAll();
    model.addAttribute("mainCategory", mainCategory);

    List<SubCategory> subCategory = this.subCategoryRepository.findAll();
    model.addAttribute("subCategory", subCategory);

    List<Post> postList = this.postRepository.findAll();
    model.addAttribute("targetPost", postList);

    return "main2";
  }

    @RequestMapping("/category/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
      SubCategory subCategory = this.subCategoryRepository.findById(id).get();
      model.addAttribute("subCategory",subCategory);

      List<Post> postList = this.postRepository.findAll();
      model.addAttribute("postList",postList);
      return "postdetail";
    }
}
