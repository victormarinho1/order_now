package com.order_now.demo.core.authentication;


import com.order_now.demo.core.exception.user.CurrentUserNotFoundException;
import com.order_now.demo.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return user;
        }
        throw new CurrentUserNotFoundException();
    }
}