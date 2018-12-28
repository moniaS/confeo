package com.example.confeo.controller;

import com.example.confeo.exception.EmailAlreadyExists;
import com.example.confeo.model.Role;
import com.example.confeo.model.User;
import com.example.confeo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

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
        model.addAttribute("roles", Arrays.asList(Role.ROLE_PARTICIPANT, Role.ROLE_ORGANIZER));
        return "login";
    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes redirectAttributes) {
        try {
            if (!isRegisterFormValid(user, redirectAttributes)) {
                redirectAttributes.addFlashAttribute("errorRegisterMessage", "Formularz rejestracji zawiera błędy");
                return "redirect:/login";
            } else {
                userService.save(user);
                redirectAttributes.addFlashAttribute("successRegisterMessage", "Konto zostało utworzone");
            }
        } catch (EmailAlreadyExists emailAlreadyExists) {
            emailAlreadyExists.printStackTrace();
            redirectAttributes.addFlashAttribute("errorRegisterMessage", "Konto z podanym adresem email już istnieje");
        }
        return "redirect:/login";
    }

    @RequestMapping("/prelegents")
    public String showPrelegents(Model model) {
        model.addAttribute("prelegents", userService.getPrelegents());
        return "prelegents";
    }

    private boolean isRegisterFormValid(User user, RedirectAttributes redirectAttributes) {
        boolean isFormValid = true;
        if (user.getFirstname() == null || user.getFirstname().equals("")) {
            isFormValid = false;
        }
        if (user.getLastname() == null || user.getLastname().equals("")) {
            isFormValid = false;
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            isFormValid = false;
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            isFormValid = false;
        }
        if (user.getRole() == null) {
            isFormValid = false;
        }
        return isFormValid;
    }
}
