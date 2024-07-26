package org.choongang.global.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setUseCodeAsDefaultMessage(true);
        // 메세지 코드가 없는 경우 코드로 메세지 대체, 편하게 사용 하기 위해 넣은 것이다
        ms.setBasenames("messages.commons", "messages.validations", "messages.errors");

        return ms;
    }
}
