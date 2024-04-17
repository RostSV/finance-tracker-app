package sk.posam.fsa.moneymate.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import sk.posam.fsa.moneymate.rest.dto.UserDto;
import sk.posam.fsa.moneymate.rest.dto.UserRoleDto;

import java.util.*;
import java.util.stream.Collectors;

public class JwtConverter extends AbstractAuthenticationToken {

    private final Jwt input;
    private static final String CLAIM_REALM_ACCESS = "realm_access";
    private static final String CLAIM_ROLES = "roles";

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
        userDto.setName(input.getClaimAsString("given_name"));
        userDto.setRole(extractRole());
        return userDto;
    }

    private UserRoleDto extractRole() {
        Map<String, Collection<String>> realmAccess = input.getClaim(CLAIM_REALM_ACCESS);
        if (realmAccess == null) return null;
        Collection<String> roles = realmAccess.get(CLAIM_ROLES);
        if (roles == null) return null;

        for (String role : roles) {
            if (getUserRoles().contains(role)) {
                return UserRoleDto.fromValue(role);
            }
        }

        throw new IllegalArgumentException("User has unknown role");
    }

    private List<String> getUserRoles() {
        return Arrays.stream(UserRoleDto.values())
                .map(UserRoleDto::getValue)
                .collect(Collectors.toList());
    }
}
