package org.choongang.global.configs;

import lombok.RequiredArgsConstructor;
import org.choongang.member.MemberUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    private final MemberUtil memberUtil;

    @Override
    public Optional<String> getCurrentAuditor() {
        String email = memberUtil.isLogin() ? memberUtil.getMember().getEmail() : null;

        return Optional.ofNullable(email); // null값을 허용하는 경우가 많이 때문에 ofNullable을 사용한다
    }
}
