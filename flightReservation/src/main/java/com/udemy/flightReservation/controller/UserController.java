package com.udemy.flightReservation.controller;

import com.udemy.flightReservation.entities.User;
import com.udemy.flightReservation.repos.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/showRegistration")
    public String showRegistrationPage(){
        return "login/registerUser";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUser(){
        return "login/login";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user")User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public String login(@RequestParam("email") String email,@RequestParam("password") String password, Model model){
        User user = userRepository.findByEmail(email);
        if(user.getPassword().equals(password)){
            return "findFlights";
        }else{
            model.addAttribute("msg", "Invalid username or password !! Please try again..");
        }
        return "login/login";
    }



}
