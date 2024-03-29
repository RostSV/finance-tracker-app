package sk.posam.fsa.moneymate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.fsa.moneymate.domain.User;
import sk.posam.fsa.moneymate.rest.dto.UserDto;
import sk.posam.fsa.moneymate.security.CurrentUserDetailService;

@RestController
public class TestController {
    public final CurrentUserDetailService currentUserDetailService;

    public TestController(CurrentUserDetailService currentUserDetailService) {
        this.currentUserDetailService = currentUserDetailService;
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/user")
    public User user() {

        return currentUserDetailService.getFullCurrentUser();
    }
}
