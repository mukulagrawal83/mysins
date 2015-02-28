package com.experiment.porter.controller;

import com.experiment.porter.resource.UserBO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public UserBO getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Logged in user name " + authentication.getName());
        return new UserBO();
    }

}
