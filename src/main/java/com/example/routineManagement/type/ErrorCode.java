package com.example.routineManagement.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_AUTHOR(HttpStatus.NOT_FOUND, "해당하는 저자를 찾을 수 없습니다."),
    NOT_FOUND_ID(HttpStatus.NOT_FOUND, "해당하는 아이디를 찾을 수 없습니다."),
    UN_MATCH_CONFIRM_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    TOO_MUCH_COMMENT(HttpStatus.BAD_REQUEST, "댓글은 최대 10개까지 작성할 수 있습니다.");

    private final HttpStatus status;
    private final String description;
}
