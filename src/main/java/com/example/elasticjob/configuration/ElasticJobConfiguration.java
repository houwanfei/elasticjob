package com.example.elasticjob.configuration;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @auther houwanfei
 * @create 2018-08-03 下午1:49
 */
@Configuration
public class ElasticJobConfiguration {
    @Autowired
    private ZooKeeperConfig zooKeeperConfig;

    @Bean("zookeeperConfiguration")
    @ConditionalOnMissingBean
    public ZookeeperConfiguration zookeeperConfiguration(){
        ZookeeperConfiguration zookeeperConfiguration =
                new ZookeeperConfiguration(zooKeeperConfig.getServerList(), zooKeeperConfig.getNamespace());
        zookeeperConfiguration.setBaseSleepTimeMilliseconds(zooKeeperConfig.getBaseSleepTimeMilliseconds());
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(zooKeeperConfig.getMaxSleepTimeMilliseconds());
        zookeeperConfiguration.setMaxRetries(zooKeeperConfig.getMaxRetries());
        zookeeperConfiguration.setSessionTimeoutMilliseconds(zooKeeperConfig.getSessionTimeoutMilliseconds());
        zookeeperConfiguration.setConnectionTimeoutMilliseconds(zooKeeperConfig.getConnectionTimeoutMilliseconds());
        zookeeperConfiguration.setDigest(zooKeeperConfig.getDigest());
        return zookeeperConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean
    @DependsOn("zookeeperConfiguration")
    public CoordinatorRegistryCenter registryCenter(){
        CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter(zookeeperConfiguration());
        registryCenter.init();
        return registryCenter;
    }
}
