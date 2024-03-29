package sk.posam.fsa.moneymate.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import sk.posam.fsa.moneymate.rest.dto.UserDto;

import java.util.Collections;
import java.util.Objects;

public class JwtConverter extends AbstractAuthenticationToken {

    private final Jwt input;

    public JwtConverter(Jwt input) {
        super(Collections.emptyList());
        this.input = Objects.requireNonNull(input);
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return Collections.emptyList();
    }

    @Override
    public Object getPrincipal() {
        UserDto userDto = new UserDto();
        userDto.setEmail(input.getClaimAsString("email"));
        return userDto;
    }
}
