package com.korea.test.post;

import com.korea.test.commend.Commend;
import com.korea.test.subcategory.SubCategory;
import com.korea.test.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;

  @ManyToOne
  private SubCategory subCategory;

  @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE )
  private List<Commend> commendList;

  @ManyToOne
  private SiteUser user;

}