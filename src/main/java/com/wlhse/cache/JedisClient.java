package com.wlhse.cache;

import com.wlhse.dto.EmployeeManagementDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
@Component
public interface JedisClient {
    String get(String key);

    String get(String key, int idx);

    String set(String key, String value);

    String setSeconds(String key, String value, int seconds);

    String setSecondsIdx(String key, String value, int seconds, int idx);

    Map<String, String> hGetAll(String key, int idx);

    void pipeLineHSet(String redisName, List<EmployeeManagementDto> list, int idx);

    long incr(String key, int seconds, int idx);

    long zadd(String key, double score, String obj, int idx);

    void zIncrByOne(String key, String obj, int idx);

    Set<String> zRevRange(String key, int idx);

    Long zCard(String key, int idx);

    Boolean exists(String key);

    void delManyCahce(String key, int idx);

    String hset(String key,Map<String,String> map);

    Map<String,String> hGetAll(String key);
}
