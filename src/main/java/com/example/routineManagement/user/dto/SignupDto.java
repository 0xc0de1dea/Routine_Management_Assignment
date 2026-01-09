package com.example.routineManagement.user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {
    @NotBlank(message = "이름은 빈칸일 수 없습니다.")
    @Size(max = 10, message = "이름은 최대 10글자까지 가능합니다.")
    private String name;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @Size(max = 100, message = "이메일은 최대 100자까지 가능합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 빈칸일 수 없습니다.")
    @Size(min = 6, max = 100, message = "비밀번호는 6자 이상 100자 이하입니다.")
    private String password;
}
