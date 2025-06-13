package com.ckzcb.ape.user.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationInit
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/6/13 14:35
 * @Version 1.0
 */
@Component
public class ApplicationInit implements ApplicationListener<ApplicationReadyEvent> {
    private Logger logger = LoggerFactory.getLogger(ApplicationInit.class);


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("application init .... ");
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
