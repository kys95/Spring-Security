package com.security.kys95.controller;

import com.security.kys95.config.auth.PrincipalDetails;
import com.security.kys95.dto.UserInfoDto;
import com.security.kys95.model.User;
import com.security.kys95.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //return view
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/"})  //localhost:8080/ or localhost:8080
    public String index(){
        return "index";
    }


    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public  String joinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(User user){
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        userRepository.save(user);

        return "redirect:/loginForm";
    }

    @GetMapping("/user")
    public @ResponseBody UserInfoDto user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .id(principalDetails.getUser().getId())
                .email(principalDetails.getUser().getEmail())
                .role(principalDetails.getUser().getRole())
                .provider(principalDetails.getUser().getProvider())
                .createDate(principalDetails.getUser().getCreateDate())
                .build();
        return userInfoDto;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/privateInfo")
    public @ResponseBody String privateInfo(){
        return "private info";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "data";
    }

}
