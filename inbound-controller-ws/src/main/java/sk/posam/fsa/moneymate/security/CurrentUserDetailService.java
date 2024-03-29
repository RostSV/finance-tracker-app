package sk.posam.fsa.moneymate.security;

import org.springframework.stereotype.Service;
import sk.posam.fsa.moneymate.rest.dto.UserDto;

@Service
public class CurrentUserDetailService {

    public UserDto getCurrentUser() {
        return new UserDto();
        
    }
}
