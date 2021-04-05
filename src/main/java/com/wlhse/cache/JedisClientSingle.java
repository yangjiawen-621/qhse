package com.wlhse.cache;

import com.alibaba.fastjson.JSON;
import com.wlhse.dto.EmployeeManagementDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

//可以使用bean的init/destroy重构该代码
@Component
public class JedisClientSingle implements JedisClient {

    @Resource
    private JedisUtils jedisUtils;

    private Jedis jedis;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    //bean的初始化与销毁
//    @PostConstruct
//    //注入前初始化
//    public void init()
//    {
//        jedis= jedisUtils.getJedis();
//        if (jedis == null) {
//            throw new NullPointerException("Jedis is Null");
//        }
//    }
//
//    @PreDestroy
//    //注入后销毁
//    public void destroy()
//    {
//        jedisUtils.returnResource(jedis);
//    }

    //Redis 字符串(String)操作
    @Override
    public String get(String key) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            return jedis.get(key);
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }

    @Override
    public String get(String key, int idx) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            jedis.select(idx);
            return jedis.get(key);
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            String string = jedis.set(key, value);
            jedis.expire(key, 3 * 86400);
            return string;
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }

    @Override
    public String setSeconds(String key, String value, int seconds) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            String string = jedis.set(key, value);
            jedis.expire(key, seconds);
            return string;
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }

    @Override
    public String setSecondsIdx(String key, String value, int seconds, int idx) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            jedis.select(idx);
            String string = jedis.set(key, value);
            jedis.expire(key, seconds);
            return string;
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }

    @Override
    public Map<String, String> hGetAll(String key, int idx) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            jedis.select(idx);
            return jedis.hgetAll(key);
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }

    @Override
    public void pipeLineHSet(String redisName, List<EmployeeManagementDto> list, int idx) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            Pipeline pipe = jedis.pipelined();
            pipe.select(idx);
            for (EmployeeManagementDto employeeManagementDto1 : list) {
                pipe.hset(redisName, employeeManagementDto1.getEmployeeID() + "", JSON.toJSONString(employeeManagementDto1));
            }
            //设置7天缓存,过期自动失效
            pipe.expire(redisName, 604800);
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
    }

    //Redis 字符串(String)
    @Override
    public long incr(String key, int seconds, int idx) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            jedis.select(idx);
            Long result = jedis.incr(key);
            jedis.expire(key, seconds);
            return result;
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return 0;
    }

    @Override
    public long zadd(String key, double score, String obj, int idx) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            jedis.select(idx);
            jedis.expire(obj, 1296000);
            return jedis.zadd(key, score, obj);
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return 0;
    }

    @Override
    public void zIncrByOne(String key, String obj, int idx) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            jedis.select(idx);
            //使用频率只统计半个月的
            jedis.expire(obj, 1296000);
            jedis.zincrby(key, 1, obj);
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
    }

    @Override
    public Set<String> zRevRange(String key, int idx) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            jedis.select(idx);
            return jedis.zrevrange(key, 0, -1);
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }

    @Override
    public Long zCard(String key, int idx) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            jedis.select(idx);
            return jedis.zcard(key);
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }

    public Boolean exists(String key) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }

    @Override
    public void delManyCahce(String key, int idx) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            jedis.select(idx);
            Set<String> keys = jedis.keys(key + "*");
            Pipeline pipe = jedis.pipelined();
            pipe.select(idx);
            for (String s : keys) {
                pipe.del(s);
            }
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
    }

    @Override
    public String hset(String key, Map<String, String> map) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            String string = jedis.hmset(key,map);
            jedis.expire(key, 1296000);
            return string;
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        }
        try {
            return jedis.hgetAll(key);
        } catch (Exception e) {
            jedisUtils.returnBrokenResource(jedis);
            logger.error(e.getMessage(), e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return null;
    }
}
