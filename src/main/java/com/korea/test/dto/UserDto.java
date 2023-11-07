package com.korea.test.dto;

import com.korea.test.user.SiteUser;
import com.korea.test.user.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


@Getter
@Setter
public class UserDto {

  @Autowired
  private UserRepository userRepository;

  private Integer id;
  private String email;
  private String password;
  private String username;
  private Integer post_id;
  private String authorize;

//  @Builder
//  public UserDto(String email, String password, String username, String authorize) {
//    this.email = email;
//    this.password = password;
//    this.username = username;
//    this.authorize = authorize;
//  }
}
