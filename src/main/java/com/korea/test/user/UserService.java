package com.korea.test.user;

import com.korea.test.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public SiteUser create(String username, String email, String password){
    SiteUser user = new SiteUser();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    user.setDateTime(LocalDateTime.now());
    user.setAuthorize("guest");
    this.userRepository.save(user);
    return user;
  }

  public SiteUser detail(Integer id){
    SiteUser userinfo = this.userRepository.findById(id).get();
    return userinfo;
  }

  public SiteUser update(Integer id, String autho){
    SiteUser userinfo = this.userRepository.findById(id).get();
    userinfo.setAuthorize(autho);
    this.userRepository.save(userinfo);
    return userinfo;
  }
}
