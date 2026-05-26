package io.eddie.jwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class MemberDetails implements OAuth2User {

    @Setter
    private Long id;

    private String email;
    private String name;

    @Setter
    private Role role;

    private Map<String, Object> attributes;

    @Builder
    public MemberDetails(String email, String name, Map<String, Object> attributes) {
        this.email = email;
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.getValue()));
    }

}
