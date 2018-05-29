package hello.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Pointcut("execution(* *.logEvent(..))")
	private void allLogEventMethods() { }

	@Before("allLogEventMethods()")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("BEFORE : " + joinPoint.getTarget().getClass().getSimpleName() + " " +
		joinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "allLogEventMethods()", returning = "retVal")
	public void logAfter(Object retVal) {
		System.out.println("Returned value: " + retVal);
	}

}
