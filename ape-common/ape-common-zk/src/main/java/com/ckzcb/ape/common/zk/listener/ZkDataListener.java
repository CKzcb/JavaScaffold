package com.ckzcb.ape.common.zk.listener;

import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;

/**
 * @ClassName ZkDataListener
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/6/6 11:19
 * @Version 1.0
 */
public interface ZkDataListener {
    void handleDataChange(String path, CuratorCacheListener.Type type, ChildData oldData, ChildData data);
}
