package com.crud4_1.config;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Auth AuthUser 를 만나면,
 * Authorization 헤더에서 JWT를 꺼내 검증 후 AuthUser를 만들어 준다.
 */
@Component
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //방법 1
        boolean hasAuthAnnotation = parameter.getParameterAnnotation(Auth.class) != null;
        boolean isAuthUserType = parameter.getParameterType().equals(AuthUser.class);

        if (hasAuthAnnotation != isAuthUserType) {
            throw new RuntimeException("@Auth와 AuthUser 타입은 함께 사용되어야 합니다.");
        }
        return hasAuthAnnotation;

        //방법 2
//        boolean hasAuth = parameter.hasParameterAnnotation(Auth.class);
//        boolean isAuthUser = parameter.getParameterType().equals(AuthUser.class);
//        return  hasAuth && isAuthUser;
    }

    @Override
    public @Nullable Object resolveArgument(MethodParameter parameter,
                                            @Nullable ModelAndViewContainer mavContainer,
                                            NativeWebRequest webRequest,
                                            @Nullable WebDataBinderFactory binderFactory
    ) throws SecurityException {

        //Authorization 헤더 꺼내기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //검증한다.
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("인증 정보가 없습니다.");
        }

        //데이터 추출(파싱)
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof Long memberId)) {
            throw new RuntimeException("인증 principal 타입이 올바르지 않습니다.");
        }

        return new AuthUser(memberId);
    }

}
