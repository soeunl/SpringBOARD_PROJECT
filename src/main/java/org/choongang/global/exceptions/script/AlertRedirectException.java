package org.choongang.global.exceptions.script;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

@Getter
public class AlertRedirectException extends AlertException {

    private String url;
    private String target;

    public AlertRedirectException(String message, String url, HttpStatus status, String target) {
        super(message, status);

        target = StringUtils.hasText(target) ? target : "self"; // 현재 창으로 고정

        this.url = url; // url 출력
        this.target = target; // 이동할 타켓 선택
    }

    public AlertRedirectException(String message, String url, HttpStatus status) {
        this(message, url, status, null);
    }
}
