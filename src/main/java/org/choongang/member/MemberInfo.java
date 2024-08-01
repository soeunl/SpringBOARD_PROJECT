package org.choongang.member;

import lombok.Builder;
import lombok.Data;
import org.choongang.member.entities.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
public class MemberInfo implements UserDetails {

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities; // 다중 권한이 있을 수 있으므로 콜렉션으로 되어있다. List나 Set 형태로 넣기 위해!
    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 권한 가져오기
        return authorities;
    }

    @Override
    public String getPassword() { // Password 가지고 옴
        return password;
    }

    @Override
    public String getUsername() { // userName 가지고 옴
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { // 계정이 만료 되지 않았니?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정이 잠겨 있지 않니?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호가 만료 되지 않았니?
        return true;
    }

    @Override
    public boolean isEnabled() { // 활성화된 계정이니?
        return true;
    }
}
