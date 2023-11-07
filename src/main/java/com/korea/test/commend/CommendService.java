package com.korea.test.commend;

import com.korea.test.post.Post;
import com.korea.test.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommendService {

  final CommendRepository commendRepository;

  public void newcommend(Post post, SiteUser siteUser, String commend){
      Commend comd = new Commend();
      comd.setCommend(commend);
      comd.setUser(siteUser);
      comd.setPost(post);
      comd.setCreateDate(LocalDateTime.now());
      this.commendRepository.save(comd);
  }

}
