package co.istad.productapidemo.service;

import co.istad.productapidemo.dto.auth.RegisterRequest;
import co.istad.productapidemo.dto.auth.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest req);
}
