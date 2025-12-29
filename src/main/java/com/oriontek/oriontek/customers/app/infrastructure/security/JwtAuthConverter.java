package com.oriontek.oriontek.customers.app.infrastructure.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtAuthConverter
        implements Converter<Jwt, Collection<SimpleGrantedAuthority>> {

    @Override
    public Collection<SimpleGrantedAuthority> convert(Jwt jwt) {

        var roles = jwt.getClaimAsMap("realm_access");

        if (roles == null || roles.get("roles") == null) {
            return List.of();
        }

        return ((Collection<String>) roles.get("roles"))
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
