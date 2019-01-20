package com.example.dingyabin.data.shard;

import com.example.dingyabin.data.config.MybatisConfig;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * Created by dingyabin001
 * Date: 2019/1/3.
 * Time:15:40
 */
public class StudentShardingDatabaseAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        long result = shardingValue.getValue() % MybatisConfig.shardDsMap.size();
        String name = MybatisConfig.shardDsMap.get(result);
        if (name != null && availableTargetNames.contains(name)) {
            return name;
        }
        return MybatisConfig.DS_COMMON;
    }

}
