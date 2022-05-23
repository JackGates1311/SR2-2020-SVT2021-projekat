package com.example.sr2_2020.svt2021.projekat.service;

import com.example.sr2_2020.svt2021.projekat.dto.ChangePasswordRequest;
import com.example.sr2_2020.svt2021.projekat.dto.RegisterRequest;
import com.example.sr2_2020.svt2021.projekat.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


public interface UserService {

    @Transactional
    public void register(RegisterRequest registerRequest);

    public User findByUsername(String username);

    @Transactional
    public ResponseEntity<ChangePasswordRequest> changePassword(ChangePasswordRequest changePasswordRequest,
                                                                HttpServletRequest request);
}
