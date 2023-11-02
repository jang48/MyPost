package com.korea.test.user;

import com.korea.test.maincategory.MainCategory;
import com.korea.test.maincategory.MainCategoryRepository;
import com.korea.test.post.Post;
import com.korea.test.post.PostRepository;
import com.korea.test.subcategory.SubCategory;
import com.korea.test.subcategory.SubCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

  @Autowired
  UserService userService;

  @Autowired
  MainCategoryRepository mainCategoryRepository;

  @Autowired
  SubCategoryRepository subCategoryRepository;

  @Autowired
  PostRepository postRepository;

  @GetMapping("/user/signup")
  public String signup(Model model, UserCreateForm userCreateForm) {

    List<MainCategory> mainCategory = this.mainCategoryRepository.findAll();
    model.addAttribute("mainCategory", mainCategory);

    List<SubCategory> subCategory = this.subCategoryRepository.findAll();
    model.addAttribute("subCategory", subCategory);

    List<Post> postList = this.postRepository.findAll();
    model.addAttribute("targetPost", postList);

    return "signup_form";
  }

  @PostMapping("/user/signup")
  public String signup(Model model,@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "signup_form";
    }

    if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
      bindingResult.rejectValue("password2", "passwordInCorrect",
          "2개의 패스워드가 일치하지 않습니다.");
      return "signup_form";
    }

    try {
      userService.create(userCreateForm.getUsername(),
          userCreateForm.getEmail(), userCreateForm.getPassword1());
    }catch(DataIntegrityViolationException e) {
      bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
      return "signup_form";
    }catch(Exception e) {
      bindingResult.reject("signupFailed", e.getMessage());
      return "signup_form";
    }

    return "redirect:/category";
  }

  @GetMapping("/login")
  public String login() {
    return "login_form";
  }

}

