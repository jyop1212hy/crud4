package com.crud4_1.config;

import java.lang.annotation.*;

/**
 * @Auth 붙은 파라미터는 ArgumentResolver가 채워준다
 */
@Target(ElementType.PARAMETER)          // 메서드 파라미터에만 사용
@Retention(RetentionPolicy.RUNTIME)     // 런타임에 리플렉션으로 읽힘
//@Documented
public @interface Auth {
}
