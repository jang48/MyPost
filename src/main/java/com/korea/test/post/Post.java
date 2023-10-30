package com.korea.test.post;

import com.korea.test.subcategory.SubCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private LocalDateTime createDate;

  @ManyToOne
  private SubCategory subCategory;

}