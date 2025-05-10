package com.ckzcb.ape.common.redis.utils;

import com.ckzcb.ape.common.redis.init.AbstractCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName SpringContextUtil
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/10 16:35
 * @Version 1.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SpringContextUtil.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
        log.info("SpringContextUtil init success");
        Map<String, AbstractCache> beans = applicationContext.getBeansOfType(AbstractCache.class);
        for (var entry : beans.entrySet()) {
            entry.getValue().init();
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
