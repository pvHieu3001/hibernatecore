package com.smartosc.training.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 01/07/2020 - 1:48 PM
 * @created_by Namtt
 * @since 01/07/2020
 */


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {

  private Long id;
  private String username;
  private String password;
  private String email;
  private Integer status;
  private Set<Role> roles;
}
