package com.ra.web.controller;

import com.ra.web.model.UserRequest;
import com.ra.web.model.entity.User;
import com.ra.web.service.FileStorageService;
import com.ra.web.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;
    private FileStorageService storageService;

    public UserController(UserService userService, ModelMapper modelMapper, FileStorageService storageService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
    }


    @GetMapping("/index")
    private String index(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> userPage = (Page<User>) userService.findAll(pageable);
        model.addAttribute("data", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        System.out.println(userPage.getTotalPages());
        return "users/index";

    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new UserRequest());
        return "users/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") UserRequest user, BindingResult bindingResult, Model model) {
        if (userService.checkExist(u -> u.getEmail().equals(user.getEmail())))
            bindingResult.addError(new FieldError("user", "email", "Email đã tồn tại!"));
        if (userService.checkExist(u -> u.getPhone().equals(user.getPhone())))
            bindingResult.addError(new FieldError("user", "email", "Số điện thoại đã tồn tại!"));
        if (bindingResult.hasErrors())
            return "users/create";
        user.setAvatar(storageService.upload(user.getImage()));
        User entity = modelMapper.map(user, User.class);
        userService.add(entity);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        User userEntity = userService.findId(id);
        UserRequest user = modelMapper.map(userEntity, UserRequest.class);
        //UserRequest user = new UserRequest();
        //user.setAddress(userEntity.getAddress());
        //user.setId(userEntity.getId());
        // ... cho đến hết các thuộc tính
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, @Valid @ModelAttribute("user") UserRequest user, Model model) {
        if (!user.getImage().isEmpty())
            user.setAvatar(storageService.upload(user.getImage()));
        User entity = modelMapper.map(user, User.class);
        userService.edit(entity);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        userService.remove(id);
        return "redirect:/users";
    }
}
