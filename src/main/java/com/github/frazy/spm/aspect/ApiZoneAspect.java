package com.github.frazy.spm.aspect;

import java.util.Arrays;
import java.util.stream.Stream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.github.frazy.spm.annotation.ApiZone;
import com.github.frazy.spm.domain.SessionUser;
import com.github.frazy.spm.enums.RoleEnum;
import com.github.frazy.spm.enums.ZoneEnum;
import com.github.frazy.spm.exception.ForbiddenException;

/**
 * @author hzyinglei
 */
@Aspect
@Component
@Order(10)
public class ApiZoneAspect {
    private static final Logger log = LoggerFactory.getLogger(ApiZoneAspect.class);

    @Around("execution(public * com.github.frazy..*.controller..*Controller.*(..))")
    public Object zone(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        ApiZone apiZone = signature.getMethod().getAnnotation(ApiZone.class);
        if (apiZone == null) {
            apiZone = (ApiZone) signature.getDeclaringType().getAnnotation(ApiZone.class);
        }
        log.info("you visit zone: {}", apiZone != null ? apiZone.value() : null);

        // Open
        if (apiZone != null && apiZone.value() != null && Stream.of(apiZone.value()).anyMatch(e -> e == ZoneEnum.OPEN)) {
            return pjp.proceed();
        }

        SessionUser user = getSessionUser();
        if (user == null) {
            throw new ForbiddenException("没有有效的会话用户");
        }
        RoleEnum roleEnum = RoleEnum.valueOf(user.getRole());
        log.info("your zone: {}", roleEnum);

        // Login
        boolean needRole = true;
        if (apiZone != null && apiZone.value() != null && Stream.of(apiZone.value()).anyMatch(e -> e == ZoneEnum.LOGIN)) {
            needRole = false;
        }

        // Others
        if (!needRole) {
            return pjp.proceed();
        }

        if (roleEnum == null) {
            throw new ForbiddenException("用户缺少角色");
        }
        if (roleEnum == RoleEnum.GOD) {
            return pjp.proceed();
        }
        if (apiZone == null || apiZone.value() == null || apiZone.value().length == 0) {
            throw new ForbiddenException("资源没有定义Zone");
        } else {
            // Zone有交集就表示有权限
            if (!CollectionUtils.containsAny(Arrays.asList(roleEnum.getZones()), Arrays.asList(apiZone.value()))) {
                throw new ForbiddenException("用户无权限");
            }
        }
        return pjp.proceed();
    }

    private SessionUser getSessionUser() {
        // TODO 取得登录用户
        SessionUser sessionUser = new SessionUser();
        sessionUser.setRole(RoleEnum.CAT_ROLE.name());
        return sessionUser;
    }

}
