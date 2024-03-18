package com.ra.web.service.impl;

import com.ra.web.NewRepo.newRepository;
import com.ra.web.model.entity.User;

import com.ra.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.function.Predicate;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private newRepository userRepository;







    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public boolean checkExist(Predicate<User> predicate) {
        return userRepository.findAll().stream()
                .anyMatch(predicate);
    }

    @Override
    public User findId(String id) {
        return userRepository.findId( id);
    }

    @Override
    public User add(User user) {
        return userRepository.add(user);
    }

    @Override
    public User edit(User user) {
        return  userRepository.edit(user);
    }

    @Override
    public boolean remove(String id) {
        return true;
    }




}
