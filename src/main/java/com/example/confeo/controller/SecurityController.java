package com.example.confeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by mstobieniecka on 2018-05-28.
 */
@Controller
public class SecurityController extends BasicController {

    @RequestMapping(value = "/login/failure")
    public String loginFailure(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorLoginMessage", "Niepoprawny adres email lub hasło");
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout")
    public String logoutSuccess() {
        session.invalidate();
        return "redirect:login";
    }
}

