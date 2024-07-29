package org.choongang.global.validators;

public interface MobileValidator {
    default boolean mobileCheck(String mobile) {
        /**
         * 01[016]-0000/000-0000
         * 01[016]-\d{3, 4}-\d{4}
         * 010.1111.1111
         * 010 1111 1111
         * 010-1111-1111
         * 01011111111
         * 핸드폰 번호 검증은 국가 마다 다르기 때문에 스프링에서 지원하지 않는다.
         * 1. 숫자만 남긴다. / 2. 패턴 만들기 / 3. 체크
         *
         */

        mobile = mobile.replaceAll("\\D", "");
        String pattern = "01[016]\\d{3,4}\\d{4}$"; // 꼭 4자로 끝날 수 있게 하기 위해 끝에 $를 썼다

        return mobile.matches(pattern);
    }
}
