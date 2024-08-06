package org.choongang.global.exceptions;

import org.choongang.global.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

public interface RestExceptionProcessor {

    @ExceptionHandler(Exception.class)
    default ResponseEntity<JSONData> errorHandler(Exception e) { // JSON 형태로 통일성 있게 처리하기 위해 형식을 고정!
        // 통일성 있게 하면 예측이 가능하다는 장점도 있다!
        // ResponseEntity를 이용한 응답 데이터 처리 → 응답에 대한 바디 정보를 상세하게 넣어줄 때 사용한다

        Object message = e.getMessage();
        // 일반적인 문자열 메세지를 담았다

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 기본 응답 코드는 500으로 설정

        if (e instanceof CommonException commonException) {
            status = commonException.getStatus(); // CommonException의 하위 클래스이면 응답코드를 가지고 와서 교체한다
            // CommonException과 하위 모두 내가 필요에 의해 정의하는 예외니까!

            Map<String, List<String>> errorMessages = commonException.getErrorMessage(); // 에러 메세지도 가지고 온다
            // 키는 String 타입, 값은 List<String> 타입을 사용한다
            // 키는 에러 필드명, 값은 해당 필드에 대한 에러 메시지 목록(한 필드에 여러개의 에러 메세지가 나올 수 있으므로)
            if (errorMessages != null ) message = errorMessages; // null이 아니면 커맨드 객체 검증을 위해 던져진 예외이다
            // null이면? 커맨드 객체 검증이 아닌 일반 에러 메세지이다
        }

        JSONData data = new JSONData(); // 통일된 형식으로 JSON 형태로 출력한다
        data.setSuccess(false); // 성공이 아닐 때
        data.setMessage(message); // 메세지를 가지고 오고
        data.setStatus(status); // 응답코드도 가지고 온다

        e.printStackTrace();

        return ResponseEntity.status(status).body(data); // 응답 코드를 직접 설정할 수 있게 넣었다
        // 상세하게 설정하기 위해 ResponseEntity 사용
        // 응답 본문에 data 객체를 포함하여 ResponseEntity 객체를 생성
        // 출력데이터를 담기 위해 body를 사용
    }
}
