package org.choongang.global.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 설정 클래스
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동 생성
@EnableConfigurationProperties(FileProperties.class)
// FileProperties 클래스의 값을 설정 파일에서 읽어와 스프링 컨테이터에 등록
public class FileConfig implements WebMvcConfigurer {

//    @Value("${file.upload.path}")
//    private String path;
//
//    @Value("${file.upload.url}")
//    private String url;

    private final FileProperties properties;
    // 범주화한 설정들을 그대로 주입 받아서 쓸 수 있다.

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(properties.getUrl() + "**")// 설정 파일에서 정의된 업로드 파일의 기본 URL을 가지고 오고, 해당 URL 패턴 아래의 모든 파일을 매핑
                .addResourceLocations("file:///" + properties);
        // file:///" + properties에 지정된 파일 시스템 경로에서 해당 파일을 찾음
    }
}
