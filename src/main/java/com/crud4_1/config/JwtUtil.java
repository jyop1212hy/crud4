package com.crud4_1.config;


import com.crud4_1.domain.user.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.crypto.SecretKey;
import java.rmi.ServerException;
import java.util.Date;

@Component
public class JwtUtil {

    private final MemberRepository memberRepository;

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    //토큰 속성
    public static final String BEARER_PREFIX = "Bearer ";
    private static final long TOKEN_TIME = 60 * 60 * 1000L;

    //비밀키 가져오기
    @Value("${jwt.secret.key}")
    private String secretKeyString;
    private SecretKey key;
    private JwtParser parser;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public JwtUtil(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //초기값 셋팅
    @PostConstruct
    public void init() {
        byte[] decode = Decoders.BASE64.decode(secretKeyString);
        this.key = Keys.hmacShaKeyFor(decode);
        this.parser = Jwts.parser()
                .verifyWith(this.key)
                .build();
    }

    //토큰 생성
    public String createToken(Long id) {
        Date now = new Date();
        return BEARER_PREFIX + Jwts.builder()
                .claim("memberId", id)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + TOKEN_TIME))
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    // 태그 제거
    public String substringToken(String tokenValue) throws ServerException {
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            return tokenValue.substring(7);
        }
        throw new ServerException("Not Found Token");
    }

    //토큰 검증
    public boolean validateToken(String token) {
        if(token == null || token.isBlank()){
            return false;
        }
        try {
            parser.parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.debug("Invalid JWT: {}", e.toString());
            return false;
        }
    }

    //토큰 파싱
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long extractMemberId(String token){
        return extractClaims(token).get("memberId", Long.class);
    }
}
