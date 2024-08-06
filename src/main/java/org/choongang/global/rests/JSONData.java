package org.choongang.global.rests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@RequiredArgsConstructor // 기본 생성자도 필요해서 추가
public class JSONData { // 초반에 JSON형식을 맞추는 것이 좋다
    // 통일성 있게 만들어야 한다
    private HttpStatus status = HttpStatus.OK; // 응답코드
    private boolean success = true; // 성공은 기본값 고정
    private Object message; // 가공이 가능 하도록 Object로 설정
    @NonNull
    private Object data;
}