package com.kutluay.user_service.service;

import com.kutluay.user_service.exception.UserException;
import com.kutluay.user_service.model.User;

import java.util.List;

public interface IUserService {

    User createUser(User user);

    User getUserById(Long id) throws Exception;

    List<User> findAll() throws UserException;

    String deleteUser(Long id);

    User updateUser(Long id, User user) throws Exception;

}
