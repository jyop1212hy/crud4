package com.crud4_1.domain.auth;

import com.crud4_1.config.JwtUtil;
import com.crud4_1.domain.user.entity.Member;
import com.crud4_1.domain.user.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public AuthService(MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 회원가입
     */
    @Transactional
    public MemberRegisterResponseDto logeUp(MemberRegisterRequestDto request) {

        String email = request.getEmail();
        String name = request.getName();
        String password = request.getPassword();

        Boolean exist = memberRepository.existsByEmail(email);
        if(exist){
            throw new IllegalArgumentException("member already exists");
        }

        // 엔터티를 통해서 데이터베이스에 저장
        Member member = new Member(email,name,password);

        // 데이터베이스에서 값을 꺼내옴
        Member sevedMember = memberRepository.save(member);

        // 반환하기
        return new MemberRegisterResponseDto(sevedMember);
    }

    public MemberLoginResponseDto login(MemberLoginRequestDto requestDto) {
        String email = requestDto.getEmail();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 없어용"));

        String token = jwtUtil.createToken(member.getId());
//        String realToken = jwtUtil.substringToken(token);

        return new MemberLoginResponseDto(token);
    }
}
