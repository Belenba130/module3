package com.ra.web.NewRepo;

import com.ra.web.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface newRepository extends JpaRepository<User, String> {
    Page<User> findAll(Pageable pageable);
    User findId(String id);
    User add(User user);
    User edit(User user);
    User remove(String id);
}

