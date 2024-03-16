package com.ra.web.service.impl;

import com.ra.web.model.entity.User;
import com.ra.web.newRepo.newRepository;
import com.ra.web.repository.Repository;
import com.ra.web.repository.impl.RepositoryImpl;
import com.ra.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private newRepository userRepository;
    @Autowired
    private Repository repository;






    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public boolean checkExist(Predicate<User> predicate) {
        return repository.findAll(User.class).stream()
                .anyMatch(predicate);
    }

    @Override
    public User findId(String id) {
        return (User) repository.findId(User.class, id);
    }

    @Override
    public User add(User user) {
        return (User) repository.add(user);
    }

    @Override
    public User edit(User user) {
        return (User) repository.edit(user);
    }

    @Override
    public boolean remove(String id) {
        return repository.remove(User.class, id);
    }




}
