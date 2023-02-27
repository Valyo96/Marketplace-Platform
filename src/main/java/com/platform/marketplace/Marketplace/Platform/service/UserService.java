package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.utility.exceptions.NotFoundException;
import com.platform.marketplace.Marketplace.Platform.model.User;
import com.platform.marketplace.Marketplace.Platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    public User getOptionalUserByEmail(String email) {
        return userRepository.getOptionalUserByEmail(email).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }



    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserByOrganizationId(Long id) {
        userRepository.deleteById(id);
    }
}
