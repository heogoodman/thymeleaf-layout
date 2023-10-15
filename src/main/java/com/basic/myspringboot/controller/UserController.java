package com.basic.myspringboot.controller;

import com.basic.myspringboot.dto.UserReqDto;
import com.basic.myspringboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @GetMapping("/thymeleaf")
    public String leaf(Model model) {
        model.addAttribute("name","스프링부트");
        return "leaf";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/users/index")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }
    @GetMapping("/users/signup")
    public String showSignUpForm(UserReqDto user) {
        return "add-user";
    }
    @PostMapping("/users/adduser")
    public String addUser(@Valid UserReqDto user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userService.saveUser(user);
        model.addAttribute("users", userService.getUsers());
        return "index";
    }
}
