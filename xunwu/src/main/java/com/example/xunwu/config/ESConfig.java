package com.example.xunwu.config;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by MrDing
 * Date: 2019/1/27.
 * Time:2:24
 */
@Configuration
public class ESConfig {

    @Resource
    private Environment environment;


    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        String hosts = environment.getProperty("xunwu.es.hosts");
        String clusterName = environment.getProperty("xunwu.es.cluster.name");
        if (hosts == null) {
            return null;
        }
        List<TransportAddress> hostList = Lists.newArrayList();
        for (String host : hosts.split(",")) {
            if (StringUtils.isBlank(host)){
                continue;
            }
            hostList.add(new InetSocketTransportAddress(InetAddress.getByName(host.trim()), 9300));
        }
        Settings settings = Settings.builder()
                                    .put("cluster.name", clusterName)
                                    .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        for (TransportAddress transportAddress : hostList) {
            client.addTransportAddress(transportAddress);
        }
        return client;
    }


}
