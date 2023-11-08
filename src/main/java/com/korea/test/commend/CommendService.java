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
      comd.setParent(0);
      comd.setIsDeleted(false);
      this.commendRepository.save(comd);
  }

  public void delcommend(Integer comid, boolean isdelete){
    Commend commend = this.commendRepository.findById(comid).get();
    commend.setIsDeleted(isdelete);
    this.commendRepository.save(commend);
  }

  public void newsubcommend(Post post, SiteUser siteUser, String commend, Integer comendid)
  {
    Commend comd = new Commend();
    comd.setCommend(commend);
    comd.setUser(siteUser);
    comd.setPost(post);
    comd.setCreateDate(LocalDateTime.now());
    comd.setParent(comendid);
    comd.setIsDeleted(false);
    this.commendRepository.save(comd);
  }

}
