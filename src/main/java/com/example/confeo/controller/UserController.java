package com.example.confeo.controller;

import com.example.confeo.model.Role;
import com.example.confeo.model.User;
import com.example.confeo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by mstobieniecka on 2018-05-26.
 */
@Controller
public class UserController extends BasicController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("registerUser", new User());
        return "login";
    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes redirectAttributes) {
        userService.save(user, Role.ROLE_PARTICIPANT);
        redirectAttributes.addFlashAttribute("successRegisterMessage", "Konto zostało utworzone");
        return "redirect:/login";
    }
}
