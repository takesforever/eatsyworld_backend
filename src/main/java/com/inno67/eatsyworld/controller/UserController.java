package com.inno67.eatsyworld.controller;

import com.inno67.eatsyworld.dto.LoginRequestDto;
import com.inno67.eatsyworld.dto.SignupRequestDto;
import com.inno67.eatsyworld.jwt.JwtTokenProvider;
import com.inno67.eatsyworld.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public String doLogin(@RequestBody LoginRequestDto requestDto){
        if(userService.login(requestDto)) {
            String token = this.jwtTokenProvider.createToken(requestDto.getUsername());
            System.out.println(token);
            return token;
        }
        else return "아이디, 비밀번호를 확인해주세요.";
    }

    @PostMapping("/signup")
    public String doSignup(@RequestBody SignupRequestDto requestDto){
        return userService.registerUser(requestDto);
    }

    public UserController (UserService userService, JwtTokenProvider jwtTokenProvider){
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

}