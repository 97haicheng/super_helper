package com.chao.helper.db.dao;


import com.chao.helper.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.TimeUnit;


@Repository
public class RedisDao {

	@Autowired
	private CacheManager cacheManager;
	
	@Autowired @Qualifier("redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
	public Object getCacheValue(String cacheName, String key) {
		Cache cache = cacheManager.getCache(cacheName);
		Cache.ValueWrapper wrapper = cache.get(key);
		if (wrapper == null) {
	       return 0L;
	    } else {
	       return JacksonUtils.toJSon(wrapper.get());
	       //return String.valueOf(wrapper.get());//ObjectUtils.getNumber(wrapper.get()).longValue();
	    }
	}
	/**
	 * 通过key模糊查询keys
	 * @param key
	 * @return
	 */
	public Set<String> getKeysSetByKey(String key){
		return  redisTemplate.keys(key);
	}
	public void delCacheValue(String key, String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		cache.evict(key);
	}
	
	public void putCacheValue(String key, String cacheName,Object value) {
		Cache cache = cacheManager.getCache(cacheName);
		cache.put(key, value);
		
	}
	  /**
     * 验证码存入到缓存中-String类型
     */
    public void putCacheValue_String(String key,Object value,String cacheName) {
    	Cache cache = cacheManager.getCache(cacheName);
		cache.put(key, (String)value);
	}
	/**
	 * 获取String类型的value
	 */
	public String getCacheValue_String(String key,String cacheName) {
	    Cache cache = cacheManager.getCache(cacheName);
	    String el = cache.get(key,String.class);
		return el;
	}
	/**
	 * 自增1返回
	 * @param key
	 * @return
	 */
	public Long increment(String key){
		return  redisTemplate.opsForValue().increment(key, 1);
	}
	/**
	 * 设置过期时间
	 * 
	 */
	public void expire(String key,int period){
		redisTemplate.expire(key, period, TimeUnit.MILLISECONDS);
	}

	/**
	 * 从左边放里面依次放入
	 * @param key
	 * @param object
	 */
	public void setInBaseListLeftPush(String key, Object object) {
		
		redisTemplate.opsForList().leftPush(key, object);
		
		redisTemplate.expire(key, 1, TimeUnit.HOURS);
		
	}
	
	/**
	 * 从右边依次取出
	 * @param key
	 * @return
	 */
	public Object getInBaseListRightPop(String key) {
		
		Object value = redisTemplate.opsForList().rightPop(key);
		return value;
	}
	
	
	/**
	 * 求栈长
	 * @param key
	 * @return
	 */
	public Long length(String key){
		return redisTemplate.opsForList().size(key);
	}

	/**
	 * 保存上一个执行方案的供应商产品id
	 * @param key
	 * @param value
	 */
	public void setPreSpCodePro(String key, String value){
		redisTemplate.opsForValue().set(key, value);
		redisTemplate.expire(key, 1, TimeUnit.HOURS);
	}
	
	/**
	 * 获取redis里的value
	 * @param key
	 */
	public String getValue(String key){
		return (redisTemplate.opsForValue().get(key)==null) ? null : redisTemplate.opsForValue().get(key).toString();
	}

}
