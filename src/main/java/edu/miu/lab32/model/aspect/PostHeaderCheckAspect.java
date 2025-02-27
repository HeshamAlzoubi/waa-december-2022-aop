package edu.miu.lab32.model.aspect;

import edu.miu.lab32.service.exception.AopIsAwesomeHeaderException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PostHeaderCheckAspect {
    private final HttpServletRequest request;

    public PostHeaderCheckAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("within(edu.miu.lab32.service.*)")
    public void a() {
    }

    @Before("a()")
    public void logActivity(JoinPoint joinPoint) throws Throwable {
        if (!request.getMethod().equals("POST")) {
            return;
        }
        // if POST request, code below should be executed
        String header = request.getHeader("AOP-IS-AWESOME");
        if (header == null) {
            throw new AopIsAwesomeHeaderException("AOP-IS-AWESOME header is missing");
        }
    }

}
