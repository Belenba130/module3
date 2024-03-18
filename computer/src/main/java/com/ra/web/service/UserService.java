package com.ra.web.service;

import com.ra.web.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Predicate;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    boolean checkExist(Predicate<User> predicate);
    User findId(String id);
    User add(User user);
    User edit(User user);
    boolean remove(String id);



}
