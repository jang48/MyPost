package com.korea.test.commend;

import com.korea.test.post.Post;
import com.korea.test.user.SiteUser;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;

import java.time.LocalDateTime;
import java.util.List;

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

  @ColumnDefault("FALSE")
  @Column(nullable = false)
  private Boolean isDeleted;

  @ColumnDefault("0")
  private Integer Parent;

  @ManyToOne
  private Post post;

  @ManyToOne
  private SiteUser user;
}
