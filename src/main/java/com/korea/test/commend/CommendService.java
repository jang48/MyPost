package com.korea.test.commend;

import com.korea.test.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommendService {
  final CommendRepository commendRepository;

}
