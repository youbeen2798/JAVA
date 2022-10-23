package com.nhnacademy.edu.springframework.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {

  @Pointcut("within(com.nhnacademy.edu.springframework.project.service..*)")
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
      //    System.out.println("." + "[" + joinPoint.getSignature().toString() + "]");
      return retVal;

    } catch (Throwable e) {
      throw new RuntimeException(e);
    } finally {
      stopWatch.stop();
      System.out.println(" [" + stopWatch.getTotalTimeMillis() + "] ms");

    }

  }

  //ASPECT랑 COMPONENT를 넣고
  //POINTCUT을 지정(SERVICE안)
  //SERVICEPOINTCUT() 생성자
  //AROUND(SERVICEPOINTCOUNT을 만듦)
  //LOGGING을 함()
  //마지막에 stop하고 vmflsxmfmf gka


}