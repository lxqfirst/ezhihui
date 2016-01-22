package com.ezhihui.www.service.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;

/**
 *
 * @author lxq
 *
 */
@Service("redisService")
public class RedisService {

    @Autowired
    private RedisTemplate<Serializable, Serializable> template;

    @Resource(name = "redisTemplate")
    private ListOperations<Serializable, Serializable> listOps;

    @Resource(name = "redisTemplate")
    private HashOperations<Serializable, Serializable, Serializable> hashOps;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Serializable> valueOps;

    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOps;

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, Serializable> zsetOps;

    private static Gson GSON = new Gson();

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /**
     *
     * @param pattern
     * @return
     * @deprecated
     */
    public Set<String> getKeys(String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        Set<String> set = new HashSet<String>(keys.size());
        for (Serializable s : keys) {
            set.add((String) s);
        }
        return set;
    }

    public <T> List<T> range(String key, long start, long end, Class<T> clazz) {
        List<Serializable> list = listOps.range(key, start, end);
        List<T> results = new ArrayList<T>(list.size());
        for (Serializable e : list) {
            results.add(GSON.fromJson((String) e, clazz));
        }

        return results;
    }

    public void trim(Serializable key, long start, long end) {
        listOps.trim(key, start, end);
    }

    public Long size(Serializable key) {
        return listOps.size(key);
    }

    public Long leftPush(String key, Object value) {
        String json = GSON.toJson(value);
        return listOps.leftPush(key, json);
    }

    public Long leftPushIfPresent(String key, Object value) {
        String json = GSON.toJson(value);
        return listOps.leftPushIfPresent(key, json);
    }

    public Long leftPush(String key, Serializable pivot, Object value) {
        String json = GSON.toJson(value);
        return listOps.leftPush(key, pivot, json);
    }

    public Long rightPush(String key, Object value) {
        String json = GSON.toJson(value);
        return listOps.rightPush(key, json);
    }

    public Long rightPushIfPresent(String key, Object value) {
        String json = GSON.toJson(value);
        return listOps.rightPushIfPresent(key, json);
    }

    public Long rightPush(String key, Serializable pivot, Object value) {
        String json = GSON.toJson(value);
        return listOps.rightPush(key, pivot, json);
    }

    public void set(String key, long index, Object value) {
        String json = GSON.toJson(value);
        listOps.set(key, index, json);
    }

    public Long remove(String key, long i, Object value) {
        String json = GSON.toJson(value);
        return listOps.remove(key, i, json);
    }

    public <T> T index(String key, long index, Class<T> classOfT) {
        String json = (String) listOps.index(key, index);
        return GSON.fromJson(json, classOfT);
    }

    public <T> T leftPop(String key, Class<T> classOfT) {
        String json = (String) listOps.leftPop(key);
        return GSON.fromJson(json, classOfT);
    }

    public <T> T leftPop(String key, long timeout, TimeUnit unit,
                         Class<T> classOfT) {
        String json = (String) listOps.leftPop(key, timeout, unit);
        return GSON.fromJson(json, classOfT);
    }

    public <T> T rightPop(Serializable key, Class<T> classOfT) {
        String json = (String) listOps.rightPop(key);
        return GSON.fromJson(json, classOfT);
    }

    public <T> T rightPop(String key, long timeout, TimeUnit unit,
                          Class<T> classOfT) {
        String json = (String) listOps.rightPop(key, timeout, unit);
        return GSON.fromJson(json, classOfT);
    }

    public <T> T rightPopAndLeftPush(String sourceKey, String destinationKey,
                                     Class<T> classOfT) {
        String json = (String) listOps.rightPopAndLeftPush(sourceKey,
                destinationKey);
        return GSON.fromJson(json, classOfT);
    }

    public <T> T rightPopAndLeftPush(Serializable sourceKey,
                                     Serializable destinationKey, long timeout, TimeUnit unit,
                                     Class<T> classOfT) {
        String json = (String) listOps.rightPopAndLeftPush(sourceKey,
                destinationKey, timeout, unit);
        return GSON.fromJson(json, classOfT);
    }

    public Boolean setIfAbsent(String key, Serializable value) {
        return valueOps.setIfAbsent(key, value);
    }

    public RedisOperations<Serializable, Serializable> getOperations() {
        return listOps.getOperations();
    }

    /**
     *
     * @param key
     * @param hkey
     * @return
     */
    public <T> T mget(String key, Object hkey, Class<T> classOfT) {
        String json = (String) hashOps.get(key, hkey);
        return GSON.fromJson(json, classOfT);
    }

    /**
     *
     * @param key
     * @param hkey
     * @return
     */
    public Integer mgetInteger(String key, Object hkey) {
        String json = (String) hashOps.get(key, hkey);
        String value = GSON.fromJson(json, String.class);
        return Integer.parseInt(value);
    }

    /**
     *
     * @param key
     * @param hkey
     * @return
     */
    public Long mgetLong(String key, Object hkey) {
        String json = (String) hashOps.get(key, hkey);
        String value = GSON.fromJson(json, String.class);
        return Long.parseLong(value);
    }

    /**
     *
     * @param key
     * @param hkey
     * @return
     */
    public Double mgetDouble(String key, Object hkey) {
        String json = (String) hashOps.get(key, hkey);
        String value = GSON.fromJson(json, String.class);
        return Double.parseDouble(value);
    }

    /**
     *
     * @param key
     * @param hkey
     * @return
     */
    public Float mgetFloat(String key, Object hkey) {
        String json = (String) hashOps.get(key, hkey);
        String value = GSON.fromJson(json, String.class);
        return Float.parseFloat(value);
    }

    /**
     *
     * @param key
     * @param hkey
     * @param hvalue
     */
    public void mput(String key, Serializable hkey, Object hvalue) {
        String json = GSON.toJson(String.valueOf(hvalue));
        hashOps.put(key, hkey, json);
    }

	/*
	 * public void mputAll(Serializable key, Map<? extends Serializable, ?
	 * extends Serializable> map) { hashOps.putAll(key, map); }
	 */

    public void putIfAbsent(Serializable key, Serializable hkey, Object hvalue) {
        String json = GSON.toJson(hvalue);
        hashOps.putIfAbsent(key, hkey, json);
    }

    /**
     *
     * @param key
     * @param hkey
     */
    public void mdelete(Serializable key, Object hkey) {
        hashOps.delete(key, hkey);
    }

    /**
     *
     * @param key
     * @param hkeys
     * @return
     */
    public <T> List<T> mmultiGet(Serializable key,
                                 Collection<Serializable> hkeys, Class<T> classOfT) {
        List<Serializable> list = hashOps.multiGet(key, hkeys);
        List<T> results = new ArrayList<T>();
        for (Serializable e : list) {
            results.add(GSON.fromJson((String) e, classOfT));
        }
        return results;
    }

    /**
     *
     * @param key
     * @return
     */
    public <T> Map<Serializable, T> mentries(Serializable key, Class<T> classOfT) {
        Map<Serializable, Serializable> map = hashOps.entries(key);

        Map<Serializable, T> resultMap = new HashMap<Serializable, T>();
        for (Map.Entry<Serializable, Serializable> e : map.entrySet()) {
            resultMap.put(e.getKey(),
                    GSON.fromJson((String) e.getValue(), classOfT));
        }

        return resultMap;
    }

    /**
     *
     * @param key
     * @return
     */
    public Map<Serializable, Integer> mentriesInteger(Serializable key) {
        Map<Serializable, Serializable> map = hashOps.entries(key);

        Map<Serializable, Integer> resultMap = new HashMap<Serializable, Integer>();
        String json = null;
        for (Map.Entry<Serializable, Serializable> e : map.entrySet()) {
            json = GSON.fromJson((String) e.getValue(), String.class);
            resultMap.put(e.getKey(), Integer.parseInt(json));
        }

        return resultMap;
    }

    /**
     *
     * @param key
     * @return
     */
    public Map<Serializable, Long> mentriesLong(Serializable key) {
        Map<Serializable, Serializable> map = hashOps.entries(key);

        Map<Serializable, Long> resultMap = new HashMap<Serializable, Long>();
        String json = null;
        for (Map.Entry<Serializable, Serializable> e : map.entrySet()) {
            json = GSON.fromJson((String) e.getValue(), String.class);
            resultMap.put(e.getKey(), Long.parseLong(json));
        }

        return resultMap;
    }

    /**
     *
     * @param key
     * @return
     */
    public Map<Serializable, Double> mentriesDouble(Serializable key) {
        Map<Serializable, Serializable> map = hashOps.entries(key);

        Map<Serializable, Double> resultMap = new HashMap<Serializable, Double>();
        String json = null;
        for (Map.Entry<Serializable, Serializable> e : map.entrySet()) {
            json = GSON.fromJson((String) e.getValue(), String.class);
            resultMap.put(e.getKey(), Double.parseDouble(json));
        }

        return resultMap;
    }

    /**
     *
     * @param key
     * @return
     */
    public Map<Serializable, Float> mentriesFloat(Serializable key) {
        Map<Serializable, Serializable> map = hashOps.entries(key);

        Map<Serializable, Float> resultMap = new HashMap<Serializable, Float>();
        String json = null;
        for (Map.Entry<Serializable, Serializable> e : map.entrySet()) {
            json = GSON.fromJson((String) e.getValue(), String.class);
            resultMap.put(e.getKey(), Float.parseFloat(json));
        }

        return resultMap;
    }

    /**
     *
     * @param key
     * @param hkey
     * @return
     */
    public boolean mhasKey(Serializable key, Object hkey) {
        return hashOps.hasKey(key, hkey);
    }

    /**
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        String json = GSON.toJson(value);
        valueOps.set(key, json);
    }

    /**
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        valueOps.set(key, GSON.toJson(value), timeout, unit);
    }

    public String get(String key) {
        Serializable value = valueOps.get(key);
        if(value == null) {
            return null;
        }

        String v = (String)value;

        return v;
    }

    public <T> T get(String key, Class<T> classOfT) {
        Serializable value = valueOps.get(key);
        if (value == null) {
            return null;
        }

        String v = (String) value;

        return GSON.fromJson(v, classOfT);
    }

    /**
     *
     * @param key
     * @param v
     */
    public void increment(String key, long v) {
        this.valueOps.increment(key, v);
    }

    public void sadd(String key, String value) {
        setOps.add(key, value);
    }

    public void sremove(String key, String value) {
        setOps.remove(key, value);
    }

    public Set<String> smembers(String key) {
        return setOps.members(key);
    }

    public boolean zadd(String key, Serializable value, Double order) {
        return zsetOps.add(key, value, order);
    }

    public long zcount(String key, Double start, Double end) {
        return zsetOps.count(key, start, end);
    }

    public Set<Serializable> zrange(String key, long start, long end) {
        return zsetOps.range(key, start, end);
    }

    public Set<Serializable> zrange(String key, double start, double end) {
        return zsetOps.rangeByScore(key, start, end);
    }

    public void zremove(String key, Object obj) {
        zsetOps.remove(key, obj);
    }

    /**
     *
     * @param key
     */
    public void delete(Serializable key) {
        this.template.delete(key);
    }

    /**
     *
     * @param key
     * @param timeout
     * @param unit
     */
    public void expire(Serializable key, long timeout, TimeUnit unit) {
        this.template.expire(key, timeout, unit);
    }

    /**
     *
     * @param key
     * @param date
     */
    public void expireAt(Serializable key, Date date) {
        this.template.expireAt(key, date);
    }

    public Boolean hasKey(Serializable key) {
        return template.hasKey(key);
    }

    public RedisTemplate<Serializable, Serializable> getTemplate() {
        return template;
    }

    public void setTemplate(RedisTemplate<Serializable, Serializable> template) {
        this.template = template;
    }

    public Set<String> members(String key) {
        return setOps.members(key);
    }

    public void delete(String key) {
        template.delete(key);
    }

    public <T> T hget(String key, String hashKey, Class<T> classOfT) {
        Serializable value = hashOps.get(key, hashKey);
        if (value == null) {
            return null;
        }

        return GSON.fromJson((String) value, classOfT);
    }

    public <T> T hget(String key, String hashKey, Type type) {
        Serializable value = hashOps.get(key, hashKey);
        if (value == null) {
            return null;
        }

        return GSON.fromJson((String) value, type);
    }

    public void hput(String key, Map<? extends Serializable, String> map) {
        if (CollectionUtils.isEmpty(map))
            return;
        hashOps.putAll(key, map);
    }

    public void hset(String key, String hashKey, Object value) {
        hashOps.put(key, hashKey, GSON.toJson(value));
    }

    public void expire(String key, long timeout, TimeUnit timeUnit) {
        template.expire(key, timeout, timeUnit);
    }

    public boolean exists(String key) {
        return false;
    }

}
