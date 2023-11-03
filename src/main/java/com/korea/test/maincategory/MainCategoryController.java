package com.korea.test.maincategory;

import com.korea.test.commend.Commend;
import com.korea.test.commend.CommendController;
import com.korea.test.commend.CommendRepository;
import com.korea.test.commend.CommendService;
import com.korea.test.post.Post;
import com.korea.test.post.PostRepository;
import com.korea.test.post.PostService;
import com.korea.test.subcategory.SubCategory;
import com.korea.test.subcategory.SubCategoryRepository;
import com.korea.test.user.SiteUser;
import com.korea.test.user.UserController;
import com.korea.test.user.UserCreateForm;
import com.korea.test.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestParam;

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
  @Autowired
  UserService userService;

  @Autowired
  CommendRepository commendRepository;

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
    public String detail(Model model, @PathVariable("id") Integer id, @RequestParam(value="page", defaultValue="0") int page) {
      List<MainCategory> mainCategory = this.mainCategoryRepository.findAll();
      model.addAttribute("mainCategory", mainCategory);

      List<SubCategory> subCategory = this.subCategoryRepository.findAll();
      model.addAttribute("subCategory", subCategory);

      List<Post> postList = this.postRepository.findAll();
      model.addAttribute("targetPost", postList);

      SubCategory subContent = this.subCategoryRepository.findById(id).get();
      model.addAttribute("subContent",subContent);

      Page<Post> paging = this.postService.getList(page,id);
      model.addAttribute("paging", paging);

      return "postdetail";
    }

    @PostMapping("/category/update")
    public String update(@RequestParam Integer subid, @RequestParam String subtitle){
      SubCategory subCategory = this.subCategoryRepository.findById(subid).get();
      subCategory.setSubtitle(subtitle);
      this.subCategoryRepository.save(subCategory);

      return "redirect:/category/detail/" + subid;
    }

    @GetMapping("/category/post/{id}")
    public String post(Model model,@PathVariable("id") Integer id){
      List<MainCategory> mainCategory = this.mainCategoryRepository.findAll();
      model.addAttribute("mainCategory", mainCategory);

      List<SubCategory> subCategory = this.subCategoryRepository.findAll();
      model.addAttribute("subCategory", subCategory);

      Post post = this.postRepository.findById(id.longValue()).get();
      model.addAttribute("post", post);

      List<Commend> commends =  this.commendRepository.findByPostId(id);
      model.addAttribute("commend",commends);
      return "post_write";
    }
}
