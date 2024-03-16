package com.ra.web.newRepo;

import com.ra.web.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface newRepository extends JpaRepository<User, String> {
    Page<User> findAll(Pageable pageable);
}

