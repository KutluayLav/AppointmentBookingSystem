package com.kutluay.user_service.service;

import com.kutluay.user_service.exception.UserException;
import com.kutluay.user_service.model.User;
import com.kutluay.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws Exception {

        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserException("User not found");
        }

        return user.get();
    }
    @Override
    public List<User> findAll() throws UserException {

        if (userRepository.findAll().isEmpty()) {
            throw new UserException("Users are Empty");
        }

        return userRepository.findAll();
    }

    @Override
    public String deleteUser(Long id) {

        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            userRepository.delete(user.get());
        }

        return "User cannot be found";
    }
    @Override
    public User updateUser(@PathVariable Long id,@RequestBody User user) throws Exception {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UserException("User not found with id");
        }

        User existingUser = userOptional.get();

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setRole(user.getRole());
        existingUser.setUserName(user.getUserName());

        return userRepository.save(user);
    }
}
