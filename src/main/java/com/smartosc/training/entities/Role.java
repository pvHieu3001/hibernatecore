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
 * testJWT
 *
 * @author Namtt
 * @created_at 01/06/2020 - 4:18 PM
 * @created_by Namtt
 * @since 01/06/2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements Serializable {
    private Long id;
    private String name;
    private Set<User> accounts;
}
