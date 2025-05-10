package com.ckzcb.ape.common.redis.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName HashCache
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/10 16:48
 * @Version 1.0
 */
@Component
public class HashCache extends AbstractCache {
    private static final Logger log = LoggerFactory.getLogger(HashCache.class);

    @Override
    public void init() {
        log.info("HashCache init");
    }
}
