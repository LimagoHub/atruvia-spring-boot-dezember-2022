package de.atruvia.mywebapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    @Pointcut(value = "execution(public * de.atruvia.mywebapp.presentation.PersonenCommandController.*(..))")
    public void personenCommandControllerMethods(){}

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceMethods(){}

    @Pointcut("@within(de.atruvia.mywebapp.aspects.Dozent)")
    public void dozentMethods(){}
}
