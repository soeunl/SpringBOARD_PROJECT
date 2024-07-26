package org.choongang.global.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

// 파일과 관련된 설정들만 모아 놓는 클래스 / 설정별로 항목을 모아 놓는다.
// 설정을 범주별로 묶어 놓는 기능도 있다.
// 범주화한 데이터 클래스 형태로 만든다!
@Data
@ConfigurationProperties(prefix = "file.upload")  // file.upload로 고정한다
public class FileProperties {
    private String path; // file.upload.path
    private String url; // file.upload.url
}

//    @Value("${file.upload.path}")
//    private String path;
//
//    @Value("${file.upload.url}")
//    private String url;
//
// 이렇게 쓰면 길고 귀찮아서 쓰지 않으려고 설정함!
