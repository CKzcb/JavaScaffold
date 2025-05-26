package com.ckzcb.ape.common.log;

import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @ClassName LogAspect
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/21 22:25
 * @Version 1.0
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "log", name = "enable", havingValue = "true", matchIfMissing = true)
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // 修正包路径：匹配任意层级的 controller 包
    @Pointcut("execution(* com.ckzcb.ape..controller.*Controller.*(..))")
    private void pointCut() {
    }

    @PostConstruct
    public void init() {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---");
    }

    // 修正环绕通知
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>---");

        // 调用目标方法并返回结果
        Object result = joinPoint.proceed();
        logger.info(">>>>>>>>>>>>>>>>>>point cut>>>>>>>> end");
        return result;
    }
}
