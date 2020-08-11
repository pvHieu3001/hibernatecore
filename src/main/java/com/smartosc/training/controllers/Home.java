package com.smartosc.training.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring-Boot-Hibernate
 *
 * @author Hieupv
 * @created_at 10/08/2020 - 11:30 AM
 * @created_by Hieupv
 * @since 10/08/2020
 */
@RestController
@RequestMapping(value = "/home")
public class Home {
    @GetMapping
    public String wellcomeHome(){
        return "Wellcome Home";
    }
}
