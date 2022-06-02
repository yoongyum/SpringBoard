package com.example.boardpractice.dto;

import com.example.boardpractice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {

    private Long seq;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

    @Pattern(regexp = "^.{2,10}$", message = "닉네임은 2~10자리여야 합니다.")
    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String name;

    private Role role;
    private String intro;

    private String age;
    private LocalDateTime createDate;
}
