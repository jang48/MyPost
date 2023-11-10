package com.korea.test.maincategory;

import com.korea.test.subcategory.SubCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class MainCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String maintitle;

  private LocalDateTime createDatetime;

  @OneToMany(mappedBy = "mainCategory",cascade = CascadeType.REMOVE)
  private List<SubCategory> subCategories;


}
