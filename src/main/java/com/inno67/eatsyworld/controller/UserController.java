package com.inno67.eatsyworld.controller;

import com.inno67.eatsyworld.dto.LoginRequestDto;
import com.inno67.eatsyworld.dto.SignupRequestDto;
import com.inno67.eatsyworld.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserService userService;

    @PostMapping("/api/login")
    public String doLogin(@RequestBody LoginRequestDto requestDto){
        // 인증 추가 후 성공 msg 대신 token 전달 예정
        if(userService.login(requestDto)) return "로그인 성공!";
        else return "아이디, 비밀번호를 확인해주세요.";
    }

    @PostMapping("/api/signup")
    public String doSignup(@RequestBody SignupRequestDto requestDto){
        return userService.registerUser(requestDto);
    }

    public UserController (UserService userService){
        this.userService = userService;
    }
}