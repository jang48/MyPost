package com.korea.test.user;

import com.korea.test.commend.Commend;
import com.korea.test.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Entity
@Setter
@Getter
public class SiteUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String username;

  private String password;

  private String authorize;

  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE )
  private List<Commend> commendList;

  @OneToMany(mappedBy = "user")
  private List<Post> postList;
}
