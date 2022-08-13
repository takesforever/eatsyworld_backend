package com.inno67.eatsyworld.service;

import com.inno67.eatsyworld.dto.LoginRequestDto;
import com.inno67.eatsyworld.dto.SignupRequestDto;
import com.inno67.eatsyworld.model.User;
import com.inno67.eatsyworld.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Boolean login(LoginRequestDto loginRequestDto) {
        User user = this.userRepository.findByUsername(loginRequestDto.getUsername()).orElse((User) null);
        if(user != null)    return true;
        else    return false;
    }

    public String registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();

        Optional<User> nameToCheck = this.userRepository.findByUsername(username);

        if(nameToCheck.isPresent()){
            return "중복된 아이디입니다.";
        } else if(!Objects.equals(password, passwordCheck)){
            return "비밀번호가 일치하지 않습니다.";
        } else{
            User user = new User(username, password);
            this.userRepository.save(user);
            return "회원가입에 성공하였습니다.";
        }
    }

}
