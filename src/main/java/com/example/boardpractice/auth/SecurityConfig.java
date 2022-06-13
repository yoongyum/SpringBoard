package com.example.boardpractice.auth;

import com.example.boardpractice.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@EnableWebSecurity  //Spring Security Setting 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    
    //리소스파일 무시
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .antMatchers("/css/**","/asset/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .httpBasic().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()//URL별 권한 관리
                    .antMatchers("/","/index","/board/view**","/member/insertMember/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                    .anyRequest().authenticated()   //anyRequest : 설정된 값들 이외 나머지 URL 나타냄, authenticated : 인증된 사용자

                .and()
                    .logout()

                    .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")  /*쿠키 제거*/
                        .clearAuthentication(true)    /*권한정보 제거*/
                        .addLogoutHandler((request, response, authentication) -> {
                                HttpSession session = request.getSession(false);
                                session.invalidate();
                        })
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()//oauth2 로그인 성공 후 가져올 때의 설정들
                            //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 등록
                            .userService(customOAuth2UserService); //리소스 서버에서 사용자정보를 가져온 상태에서 추가로 진행하고자하는 기능 명시
        super.configure(http);
    }
}
