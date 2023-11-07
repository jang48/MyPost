package com.korea.test.maincategory;

import com.korea.test.subcategory.SubCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class MainCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String maintitle;

  @OneToMany(mappedBy = "mainCategory",cascade = CascadeType.REMOVE)
  private List<SubCategory> subCategories;


}
