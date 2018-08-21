package com.example.elasticjob.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @auther houwanfei
 * @create 2018-08-03 下午1:39
 */
@ConfigurationProperties(prefix = "setting.job.zookeeper")
@Component
@Data
public class ZooKeeperConfig {
    private String serverList;

    private String namespace;

    /**
     * 等待重试的间隔时间的初始值.
     * 单位毫秒.
     */
    private int baseSleepTimeMilliseconds = 1000;

    /**
     * 等待重试的间隔时间的最大值.
     * 单位毫秒.
     */
    private int maxSleepTimeMilliseconds = 3000;

    /**
     * 最大重试次数.
     */
    private int maxRetries = 3;

    /**
     * 会话超时时间.
     * 单位毫秒.
     */
    private int sessionTimeoutMilliseconds;

    /**
     * 连接超时时间.
     * 单位毫秒.
     */
    private int connectionTimeoutMilliseconds;

    /**
     * 连接Zookeeper的权限令牌.
     * 缺省为不需要权限验证.
     */
    private String digest;
}
