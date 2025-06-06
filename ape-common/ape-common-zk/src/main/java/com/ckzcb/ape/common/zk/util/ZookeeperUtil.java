package com.ckzcb.ape.common.zk.util;

import com.ckzcb.ape.common.zk.listener.ZkDataListener;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.springframework.stereotype.Component;

/**
 * @ClassName ZookeeperUtil
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/6/5 18:18
 * @Version 1.0
 */
@Component
public class ZookeeperUtil {

    @Resource
    private CuratorFramework curatorFramework;

    private CuratorCache curatorCache;

    @PostConstruct
    public void init() {
        System.out.println("ZookeeperUtil init");
    }

    public void getDataAndWatch(String path, ZkDataListener zkDataListener) throws Exception {
        curatorCache = CuratorCache.builder(curatorFramework, path).build();
        curatorCache.listenable().addListener((type, oldData, data) -> zkDataListener.handleDataChange(path, type, oldData, data));
        curatorCache.start();
    }

}
