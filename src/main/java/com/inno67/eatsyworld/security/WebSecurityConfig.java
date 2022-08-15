package com.inno67.eatsyworld.security;

import com.inno67.eatsyworld.jwt.JwtAuthenticationFilter;
import com.inno67.eatsyworld.jwt.JwtTokenProvider;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true
)
public class WebSecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

     // (기본설정) 스프링부트가 제공하는 static 리소스들의 기본위치를 가져와 스프링 시큐리티에서 제외
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers("/docs/index.html");
        web.ignoring().requestMatchers(new RequestMatcher[]{PathRequest.toStaticResources().atCommonLocations()});
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/scripts/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                // 세션인증 사용하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 토큰인증 먼저 실행
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
            return http.build();

//        //기존 코드
//        ((HttpSecurity) ((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
//                ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
//                        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
//                                ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
//                                        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
//                                                ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
//                                                        http.authorizeRequests().antMatchers(new String[]{"/api/**"})).permitAll()
//                                                        .antMatchers(new String[]{"**"})).permitAll()
//                                                .antMatchers(new String[]{"/"})).permitAll()
//                                        .antMatchers(HttpMethod.GET, new String[]{"/api/contents"}))
//                                .permitAll().antMatchers(HttpMethod.GET, new String[]{"/api/reply/**"}))
//                        .permitAll().anyRequest()).authenticated()
//                .and())
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and())
//                .addFilterBefore(new JwtAuthenticationFilter(this.jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
}
