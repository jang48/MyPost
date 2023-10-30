package com.korea.test.subcategory;

import com.korea.test.maincategory.MainCategory;
import com.korea.test.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class SubCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String subtitle;

  @ManyToOne
  private MainCategory mainCategory;

  @OneToMany(mappedBy = "subCategory",cascade = CascadeType.REMOVE)
  private List<Post> post;
}
