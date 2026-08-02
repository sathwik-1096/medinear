package com.medinear.medinear.service;

import com.medinear.medinear.dto.LoginRequestDto;
import com.medinear.medinear.dto.LoginResponseDto;
import com.medinear.medinear.dto.RegisterRequestDto;

public interface AuthenticationService {

    LoginResponseDto register(RegisterRequestDto request);

    LoginResponseDto login(LoginRequestDto request);
}