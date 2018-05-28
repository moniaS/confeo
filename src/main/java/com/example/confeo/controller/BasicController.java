package com.example.confeo.controller;

import com.example.confeo.model.User;
import com.example.confeo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by mstobieniecka on 2018-05-28.
 */
public abstract class BasicController {
    protected final String className = getClass().getSimpleName();
    protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    protected UserService userService;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;

    @ModelAttribute("currentUser")
    protected User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return null;
        String username = authentication.getName();
        if (username == null)
            return null;
        User user = userService.findByUsername(username);
        if (user == null)
            return null;
        return user;
    }
}

