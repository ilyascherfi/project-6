package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.model.dto.auth.LoginResponse;

public interface IUserService {
  LoginResponse findByEmail(String email);
}