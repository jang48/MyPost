package com.korea.test.commend;

import com.korea.test.post.Post;
import com.korea.test.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Commend {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "TEXT")
  private String commend;

  private LocalDateTime createDate;

  @ManyToOne
  private Post post;

  @ManyToOne
  private SiteUser user;
}
