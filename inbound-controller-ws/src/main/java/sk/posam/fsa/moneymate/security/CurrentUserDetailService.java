package sk.posam.fsa.moneymate.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.domain.service.UserFacade;
import sk.posam.fsa.moneymate.rest.dto.UserDto;
import sk.posam.fsa.moneymate.rest.dto.UserRoleDto;

@Service
public class CurrentUserDetailService {

    private final UserFacade userFacade;

    public CurrentUserDetailService(UserFacade userFacade) {
        this.userFacade = userFacade;
    }


    public UserDto getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDto) {
            return (UserDto) principal;
        }

        throw new IllegalStateException("Current user is not UserDto.");
    }

    public User getFullCurrentUser() {
        return userFacade.findByEmail(getUserEmail());
    }


    public boolean isAdmin() {
        return getCurrentUser().getRole() == UserRoleDto.ADMIN;
    }

    private String getUserEmail() {
        return getCurrentUser().getEmail();
    }
}
