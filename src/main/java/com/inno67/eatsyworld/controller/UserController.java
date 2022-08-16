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
    UserService userService;
    JwtTokenProvider jwtTokenProvider;

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

//    @GetMapping("/logout")
//    public String doLogout(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        if(userService.logout(userDetails))
//    }
//
//    public Boolean logout(ServletRequest request, ServletResponse response, FilterChain chain){
//        // 요청 헤더에서 토큰 가져옴
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//        // 토큰이 null 이 아니거나 유효한 토큰이 아닌 경우
//        if (token != null && this.jwtTokenProvider.validateToken(token)) {
//            // 인증 객체에 null 토큰 넣기
//            Authentication authentication = this.jwtTokenProvider.getAuthentication(null);
//            // security contextHolder 초기화
//            SecurityContextHolder.clearContext();
//            return true;
//        } else{
//            return false;
//        }
//    }
}