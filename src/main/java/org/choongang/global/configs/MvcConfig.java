package org.choongang.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing // 엔티티의 생성일자(createdDate)와 수정일자(lastModifiedDate)를 자동으로 관리할 수 있도록 도와주는 기능을 활성화
@Configuration
@EnableScheduling
public class MvcConfig implements WebMvcConfigurer {

    /**
     * <input type="hidden" name="_method" value="PATCH"> -> PATCH 방식으로 요청
     * ?_method=DELETE
     * @return
     */

    @Bean // 빈으로 꼭 등록해야지 활성화 됩니다!
    public HiddenHttpMethodFilter httpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    // HTML form에서 PUT, DELETE 등의 메서드를 지원하기 위해 사용
    // 기본적으로 HTTP는 GET과 POST만 지원하기 때문에, 이 필터를 사용하면 form의 method 속성을 _method 파라미터로 전달하여 실제 HTTP 메서드를 변경할 수 있음
}
