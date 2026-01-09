package com.example.routineManagement.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당하는 이름의 유저를 찾을 수 없습니다."),
    NOT_FOUND_AUTHOR(HttpStatus.NOT_FOUND, "해당하는 저자를 찾을 수 없습니다."),
    NOT_FOUND_ID(HttpStatus.NOT_FOUND, "해당하는 아이디를 찾을 수 없습니다."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다."),
    INVALID_ACCESS(HttpStatus.UNAUTHORIZED, "허용되지 않는 접근입니다.");

    private final HttpStatus status;
    private final String description;
}
