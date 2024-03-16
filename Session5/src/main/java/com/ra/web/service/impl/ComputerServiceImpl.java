package com.ra.web.service.impl;

import com.ra.web.model.entity.Computer;
import com.ra.web.repository.Repository;
import com.ra.web.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    private Repository<Computer> computerRepository;


    @Override
    public List<Computer> findAll() {
        return computerRepository.findAll(Computer.class);
    }
}
