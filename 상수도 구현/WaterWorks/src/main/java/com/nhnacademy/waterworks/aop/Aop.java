package com.nhnacademy.waterworks.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {
  @Pointcut("within(com.nhnacademy.waterworks.service..*)")
  private void PointCut(){
  }
  @Around("Aop.PointCut()")
  public Object aop(ProceedingJoinPoint joinPoint) {
    StopWatch stopWatch = new StopWatch();
    try {
      stopWatch.start();

      Object retVal = joinPoint.proceed();

      String className = joinPoint.getTarget().getClass().getName();
      String arr[] = className.split("[.]");
      className = arr[arr.length -1];

      System.out.print("[" + className +"]");
      System.out.print(".[" + joinPoint.getSignature().getName() + "]");
      return retVal;

    } catch (Throwable e) {
      throw new RuntimeException(e);
    } finally {
      stopWatch.stop();
      System.out.println(" [" + stopWatch.getTotalTimeMillis() + "] ms");
    }
  }

}