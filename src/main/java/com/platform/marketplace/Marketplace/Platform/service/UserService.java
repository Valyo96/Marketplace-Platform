package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.exceptions.NotFoundException;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.userNotFound;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(userNotFound));
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException(userNotFound));
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
