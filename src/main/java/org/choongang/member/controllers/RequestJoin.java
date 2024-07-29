package org.choongang.member.controllers;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestJoin { // 이곳은 커맨드 객체
    // 1차 검증은 커맨드 객체에서~

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String userName;
    @NotBlank
    private String mobile;
    @AssertTrue
    private boolean agree;
}
