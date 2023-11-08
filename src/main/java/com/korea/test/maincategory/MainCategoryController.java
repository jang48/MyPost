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
import com.korea.test.user.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.SpinnerUI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
  CommendRepository commendRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  CommendService commendService;



  @RequestMapping("/login")
  public String main2() {
    return "login_form";
  }

  @PreAuthorize("isAuthenticated()")
  @RequestMapping("/home")
  public String home(Model model, Authentication authentication) {
    List<MainCategory> mainCategory = this.mainCategoryRepository.findAll();
    model.addAttribute("mainCategory", mainCategory);

    List<SubCategory> subCategory = this.subCategoryRepository.findAll();
    model.addAttribute("subCategory", subCategory);

    List<Post> postList = this.postRepository.findAll();
    model.addAttribute("targetPost", postList);

    model.addAttribute("authority", getAuthority(authentication));
    return "main_home";
  }

  private String getAuthority(Authentication authentication) {
    List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
    return authorities.get(0).toString();
  }

  @PreAuthorize("isAuthenticated()")
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

    return "post_detail";
    }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/category/update")
  public String update(@RequestParam Integer subid, @RequestParam String subtitle){
    SubCategory subCategory = this.subCategoryRepository.findById(subid).get();
    subCategory.setSubtitle(subtitle);
    this.subCategoryRepository.save(subCategory);
    return "redirect:/category/detail/" + subid;
  }

  @PreAuthorize("isAuthenticated()")
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

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/commend/new")
  public String addcommed(@RequestParam Integer postid,  @RequestParam String username,@RequestParam String commend){
    Post post = this.postRepository.findById(postid.longValue()).get();
    SiteUser siteUser =  this.userRepository.findByusername(username).get();
    this.commendService.newcommend(post,siteUser,commend);
    return "redirect:/category/post/" + postid;
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/commend/del/{postid}/{comid}")
  public String delcommed(@PathVariable Integer postid,  @PathVariable Integer comid){
    Commend commend = this.commendRepository.findById(comid).get();
    this.commendRepository.delete(commend);
    return "redirect:/category/post/" + postid;
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/commend/delsub/{postid}/{comid}")
  public String delcommedcommed(@PathVariable Integer postid,  @PathVariable Integer comid){
    this.commendService.delcommend(comid, true);
    return "redirect:/category/post/" + postid;
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/commend/subcommend/new")
  public String addsubcommed(@RequestParam Integer postid,  @RequestParam String username, @RequestParam String commend, @RequestParam Integer commendid){
    Post post = this.postRepository.findById(postid.longValue()).get();
    System.out.println(username);
    SiteUser siteUser =  this.userRepository.findByusername(username).get();
    this.commendService.newsubcommend(post,siteUser,commend,commendid);
    return "redirect:/category/post/" + postid;
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/commend/upd")
  public String updcommed(Model model, @RequestParam Integer postid, @RequestParam Integer comid,  @RequestParam String subcommend){
    Commend commend = this.commendRepository.findById(comid).get();
    commend.setCommend(subcommend);
    this.commendRepository.save(commend);
    return "redirect:/category/post/" + postid;
  }
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/host/manage")
  public String manage(Model model){
    List<MainCategory> mainCategory = this.mainCategoryRepository.findAll();
    model.addAttribute("mainCategory", mainCategory);

    List<SubCategory> subCategory = this.subCategoryRepository.findAll();
    model.addAttribute("subCategory", subCategory);

    return "host_manage";
  }
}
