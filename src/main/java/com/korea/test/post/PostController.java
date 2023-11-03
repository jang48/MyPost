package com.korea.test.post;

import com.korea.test.subcategory.SubCategory;
import com.korea.test.subcategory.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PostController {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private SubCategoryRepository subCategoryRepository;

  @Autowired
  private PostService postService;
  @RequestMapping("/test")
  @ResponseBody public String test() {
    return "test";
  }

  @RequestMapping("/")
  public String main(Model model, Authentication authentication) {

    //1. DB에서 데이터 꺼내오기
    List<Post> postList = this.postRepository.findAll();
    //2. 꺼내온 데이터를 템플릿으로 보내기
    model.addAttribute("postList", postList);
    model.addAttribute("targetPost", postList.get(0));

    return "main";
  }

  @PostMapping("/write")
  public String write() {
    Post post = new Post();
    post.setTitle("new title..");
    post.setContent("");
    post.setCreateDate(LocalDateTime.now());

    this.postRepository.save(post);
    return "redirect:/";
  }

  @GetMapping("/detail/{id}")
  public String detail(Model model, @PathVariable Long id) {
    Post post = this.postRepository.findById(id).get();
    model.addAttribute("targetPost", post);
    model.addAttribute("postList", this.postRepository.findAll());

    return "main";
  }
  @PostMapping("/update")
  public String update(@RequestParam Long id,@RequestParam  String title,@RequestParam String content) {
    Post post = this.postRepository.findById(id).get();
    post.setTitle(title);
    post.setContent(content);

    this.postRepository.save(post);
    return "redirect:/detail/" + id;
  }

  @PostMapping("/delete")
  public String delete (Long id){
    Post post = this.postRepository.findById(id).get();
    this.postRepository.delete(post);

    return "redirect:/";
  }

  @PostMapping("/category/postadd")
  public String postadd(@RequestParam Integer subid){
    SubCategory subCategory = this.subCategoryRepository.findById(subid).get();
    this.postService.createpost(subCategory);
    return "redirect:/category/detail/" + subid;
  }

}