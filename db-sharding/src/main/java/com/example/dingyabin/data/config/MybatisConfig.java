package com.example.dingyabin.data.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.dingyabin.data.shard.StudentShardingDatabaseAlgorithm;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by dingyabin001
 * Date: 2018/9/6.
 * Time:11:57
 */
@Configuration
@MapperScan("com.example.dingyabin.data.dao")
public class MybatisConfig {

    private static final String DS_00 = "ds_00";

    private static final String DS_01 = "ds_01";

    public static final String DS_COMMON = "ds_common";

    public static Map<Long, String> shardDsMap;

    static {
        shardDsMap = new HashMap<>();
        shardDsMap.put(0L, DS_00);
        shardDsMap.put(1L, DS_01);
        shardDsMap = Collections.synchronizedMap(shardDsMap);
    }

    @Bean
    @ConfigurationProperties(prefix = "druid.datasouce00")
    public DataSource dataSource00() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "druid.datasouce01")
    public DataSource dataSource01() {
        return new DruidDataSource();
    }


    @Bean
    @ConfigurationProperties(prefix = "druid.datasouce.common")
    public DataSource dataSourceCommon() {
        return new DruidDataSource();
    }


    @Bean
    @Primary
    public DataSource dataSourceMain() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getStudentTableRuleConfiguration());
        //默认数据库分库策略
        //shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig();
        //默认数据源
        shardingRuleConfig.setDefaultDataSourceName(MybatisConfig.DS_COMMON);
        //默认分表策略
        //shardingRuleConfig.setDefaultTableShardingStrategyConfig();
        return ShardingDataSourceFactory.createDataSource(dataSourceMap(), shardingRuleConfig, new HashMap<>(), new Properties());
    }


    private static TableRuleConfiguration getStudentTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", StudentShardingDatabaseAlgorithm.class.getName()));
        result.setLogicTable("student");
        return result;
    }


    @Bean
    public Map<String, DataSource> dataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put(DS_00, dataSource00());
        result.put(DS_01, dataSource01());
        result.put(DS_COMMON, dataSourceCommon());
        return result;
    }

}
