package com.ckzcb.ape.user.config;

import com.ckzcb.ape.user.service.ConditionService;
import com.ckzcb.ape.user.service.impl.ConditionServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ConditionConfigure
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/4/18 23:08
 * @Version 1.0
 */
@Configuration
public class ConditionConfigure {
    @Bean
    @ConditionalOnProperty(name = {"user.service.lock"}, havingValue = "true", matchIfMissing = false)
    public ConditionService getConditionService() {
        return new ConditionServiceImpl();
    }
}
