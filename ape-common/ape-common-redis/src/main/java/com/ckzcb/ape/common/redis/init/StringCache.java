package com.ckzcb.ape.common.redis.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName StringCache
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/10 16:48
 * @Version 1.0
 */
@Component
public class StringCache extends AbstractCache {
    private static final Logger log = LoggerFactory.getLogger(StringCache.class);

    @Override
    public void init() {
        log.info("StringCache init success");
    }
}
