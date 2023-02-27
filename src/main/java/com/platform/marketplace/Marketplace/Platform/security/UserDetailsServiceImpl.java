package com.platform.marketplace.Marketplace.Platform.security;

import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.USER_NOT_FOUND;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userRepository.getUserByEmail(username).orElseThrow(() ->  new UsernameNotFoundException(USER_NOT_FOUND));
        return new MyUserDetails(user);
    }
}
