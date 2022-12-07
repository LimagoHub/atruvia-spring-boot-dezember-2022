package de.atruvia.mywebapp.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {



    @Before("PointCuts.serviceMethods()")
    public void logAdvice(JoinPoint joinPoint) {
        log.warn(joinPoint.getSignature().getName() + " wurde gerufen");
    }

    @AfterThrowing(value = ("PointCuts.personenCommandControllerMethods()"), throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
        log.error("{}",ex);
    }

    @AfterReturning(value = ("execution(public * de.atruvia.mywebapp.presentation.PersonenCommandController.*(..))"), returning = "retval")
    public void afterReturningAdvice(JoinPoint joinPoint, Object retval) {
        log.error("{}",retval);
    }

    @Around(value = ("execution(public * de.atruvia.mywebapp.presentation.PersonenCommandController.*(..))"))
    public Object afterReturningAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        var result = joinPoint.proceed();
        return result;
    }

}
