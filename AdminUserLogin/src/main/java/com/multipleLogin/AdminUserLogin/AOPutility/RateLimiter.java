package com.multipleLogin.AdminUserLogin.AOPutility;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
//@Aspect
//@Component
public class RateLimiter {

    private Map<String, Long> requests = new HashMap<>();
    private final int MAX_REQUESTS_PER_SECOND = 1;

    @Around("@annotation(RateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String email = (String) args[0];

        Long lastRequestTime = requests.getOrDefault(email, 0L);
        long now = System.currentTimeMillis();

        if (now - lastRequestTime < 1000 / MAX_REQUESTS_PER_SECOND) {
            throw new Throwable("Rate limit exceeds");
        }

        requests.put(email, now);

        return joinPoint.proceed();
    }
}

