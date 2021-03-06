package com.security.kys95.config.auth;

import com.security.kys95.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Security Session에 session 정보를 저장 -> 들어갈 수 있는 객체는 Authentication type
 * Authentication 객체 안에 User정보를 저장 -> 들어갈 수 있는 객체는 UserDetails type(PrincipalDetails type)
 */
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user;  //composition
    private Map<String, Object> attributes;

    //not OAuth Login
    public PrincipalDetails(User user) {
        this.user = user;
    }

    //OAuth Login
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    //해당 user의 권한 return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        //ex)회원이 1년동안 로그인을 안하면 휴먼 계정으로 돌림
        //if user의 현재시간-로그인시간 > 1년, return false;
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }
}
