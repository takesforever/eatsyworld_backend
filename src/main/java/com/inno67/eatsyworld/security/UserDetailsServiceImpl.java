package com.inno67.eatsyworld.security;

import com.inno67.eatsyworld.model.User;
import com.inno67.eatsyworld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User)this.userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("Can't find " + username);
        });
        return new UserDetailsImpl(user);
    }
}
