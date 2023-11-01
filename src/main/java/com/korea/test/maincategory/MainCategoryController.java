package com.korea.test.maincategory;

import com.korea.test.post.Post;
import com.korea.test.post.PostRepository;
import com.korea.test.post.PostService;
import com.korea.test.subcategory.SubCategory;
import com.korea.test.subcategory.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
  @Autowired
  PostService postService;


  @RequestMapping("/category")
  public String main2(Model model) {
    List<MainCategory> mainCategory = this.mainCategoryRepository.findAll();
    model.addAttribute("mainCategory", mainCategory);

    List<SubCategory> subCategory = this.subCategoryRepository.findAll();
    model.addAttribute("subCategory", subCategory);

    List<Post> postList = this.postRepository.findAll();
    model.addAttribute("targetPost", postList);

    return "mainlist";
  }

    @RequestMapping("/category/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
      List<MainCategory> mainCategory = this.mainCategoryRepository.findAll();
      model.addAttribute("mainCategory", mainCategory);

      List<SubCategory> subCategory = this.subCategoryRepository.findAll();
      model.addAttribute("subCategory", subCategory);

      List<Post> postList = this.postRepository.findAll();
      model.addAttribute("targetPost", postList);

      SubCategory subContent = this.subCategoryRepository.findById(id).get();
      model.addAttribute("subContent",subContent);

      List<Post> post = this.postRepository.findBySubCategoryId(id);
      model.addAttribute("post", post);


      return "postdetail";
    }

    @PostMapping("/category/update")
    public String update(@RequestParam Integer subid, @RequestParam String subtitle){
      SubCategory subCategory = this.subCategoryRepository.findById(subid).get();
      subCategory.setSubtitle(subtitle);
      this.subCategoryRepository.save(subCategory);
     return "redirect:/category/detail/" + subid;
    }


}
