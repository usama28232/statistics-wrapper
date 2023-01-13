package com.practice.web.logger.aspects;

import com.practice.dto.LogSummaryData;
import com.practice.web.logger.store.Summary;
import com.practice.web.utils.Utils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.JamonPerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class LoggerAspect {

    private static final String CLASS_EXP = "com.practice.web.logger.aspects.LoggerAspect.montitor()";

    @SuppressWarnings("unused")
    @Pointcut("execution(* com.practice.web.services.*.*(..)) && !within(com.practice.web.services.CacheService)")
    public void montitor() throws Throwable {
    }

    @Bean
    public JamonPerformanceMonitorInterceptor performanceMonitorInterceptor() {
        return new JamonPerformanceMonitorInterceptor(true, true);
    }

    @Bean
    public Advisor performanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(CLASS_EXP);
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }

    @Around("@annotation(com.practice.annotations.LogSummary)")
    public Object logStartTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String[] args = getArgs(joinPoint);
        String key = Utils.keyMaker(args);
        LogSummaryData logSummaryData = new LogSummaryData(
                System.currentTimeMillis(),
                0,
                0,
                key,
                (String) joinPoint.getArgs()[args.length]
        );
        Object ret = joinPoint.proceed();
        logSummaryData.setEndTime(System.currentTimeMillis());
        logSummaryData.setDuration(
                logSummaryData.getEndTime() - logSummaryData.getStartTime()
        );
        Summary.save(key, logSummaryData);
        return ret;
    }

    @After("@annotation(com.practice.annotations.LogSummaryPreview)")
    public void printLogSummary(JoinPoint joinPoint) {
        Summary.printSummary();
    }

    private String[] getArgs(ProceedingJoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs()).limit(3).toArray(String[]::new);
    }

}
