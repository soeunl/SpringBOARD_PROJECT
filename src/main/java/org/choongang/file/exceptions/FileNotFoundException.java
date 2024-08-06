package org.choongang.file.exceptions;

import org.choongang.global.exceptions.script.AlertBackException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends AlertBackException {
    public FileNotFoundException() {
        super("NoFound.file", HttpStatus.NOT_FOUND); // 다국어 사이트의 경우에는 코드가 필요하기 때문에 코드를 사용
        setErrCode(true);
    }
}
