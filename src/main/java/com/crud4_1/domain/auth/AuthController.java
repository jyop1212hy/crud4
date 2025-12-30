package com.crud4_1.domain.auth;

import com.crud4_1.common.apiResponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.ServerException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 회원가입
     */
    @PostMapping("/logeUp")
    public ResponseEntity<ApiResponse<MemberRegisterResponseDto>> logeUp(@RequestBody MemberRegisterRequestDto request) {
        MemberRegisterResponseDto responseDto = authService.logeUp(request);
        ApiResponse<MemberRegisterResponseDto> apiResponse = new ApiResponse<>("created", 201, responseDto);
        ResponseEntity<ApiResponse<MemberRegisterResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        return response;
    }


    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) throws ServerException {
        MemberLoginResponseDto login = authService.login(requestDto);
        ResponseEntity response = new ResponseEntity(login, HttpStatus.OK);
        return response;
    }
}
