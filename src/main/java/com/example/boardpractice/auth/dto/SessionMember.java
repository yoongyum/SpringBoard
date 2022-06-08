package com.example.boardpractice.auth.dto;

import com.example.boardpractice.domain.Member;
import com.example.boardpractice.domain.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SessionMember implements Serializable {

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

//    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

//    @Pattern(regexp = "^.{2,10}$", message = "닉네임은 2~10자리여야 합니다.")
//    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String name;

    private String picture;

    private Role role;
    private String intro;

    private String age;
    private LocalDateTime createDate;

    public SessionMember(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.intro = member.getIntro();
        this.role = member.getRole();
    }
}
