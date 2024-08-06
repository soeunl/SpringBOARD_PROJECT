package org.choongang.global.exceptions.script;

import org.choongang.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends CommonException {
    public UnAuthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

    public UnAuthorizedException() {
        this("Unauthorized");
        setErrCode(true);
    }
}
