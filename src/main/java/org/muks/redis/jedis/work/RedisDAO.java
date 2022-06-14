//package org.muks.redis.jedis.work;
//
//import org.apache.commons.pool.impl.GenericObjectPool;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import com.comut.utils.exception.CacheException;
//import com.comut.utils.interfaces.IRedis;
//
//public class RedisDAO extends AbstractDAO implements IRedis{
//
//  /**
//   *
//   */
//  private static final long serialVersionUID = 1L;
//
//  private static final Integer PORT = 6379;
//
//  private static final String HOST = "10.0.0.125";
//
//  private JedisPool jedisPool = null;
//
//  private JedisPool getJedis(){
//    if(this.jedisPool == null){
//      GenericObjectPool.Config poolConfig = new GenericObjectPool.Config();
//      poolConfig.maxActive = 100;
//      poolConfig.maxIdle = 10;
//      poolConfig.minIdle = 2;
//      poolConfig.maxWait = 100;
//      poolConfig.testWhileIdle = true;
//      poolConfig.testOnBorrow = true;
//      poolConfig.testOnReturn = true;
//      poolConfig.minEvictableIdleTimeMillis = 10000;
//      poolConfig.timeBetweenEvictionRunsMillis = 5000;
//      poolConfig.numTestsPerEvictionRun = 10;
//
//      this.jedisPool = new JedisPool(poolConfig, HOST, PORT);
//    }
//    return this.jedisPool;
//  }
//
//  /**
//   * Get the cache client
//   * @return the {@link Jedis}
//   * @throws Exception
//   */
//  private Jedis getCache(){
//    return this.getJedis().getResource();
//  }
//
//  private void returnCache(Jedis cache){
//    this.getJedis().returnResource(cache);
//  }
//
//  @Override
//  public String get(String key) throws CacheException{
//    Jedis cache = this.getCache();
//    try{
//      return cache.get(key);
//    } finally {
//      this.returnCache(cache);
//    }
//  }
//
//  @Override
//  public void set(String key, String value){
//    Jedis cache = this.getCache();
//    try{
//      cache.set(key, value);
//    } finally {
//      this.returnCache(cache);
//    }
//
//  }
//
//  @Override
//  public void del(String key){
//    Jedis cache = this.getCache();
//    try{
//      cache.del(key);
//    } finally {
//      this.returnCache(cache);
//    }
//  }
//
//  @Override
//  public void flush(){
//    Jedis cache = this.getCache();
//    try{
//      cache.flushAll();
//    } finally {
//      this.returnCache(cache);
//    }
//  }
//
//  @Override
//  public Set<String> keys(String pattern){
//    Jedis cache = this.getCache();
//    try{
//      return cache.keys(pattern);
//    } finally {
//      this.returnCache(cache);
//    }
//  }
//
//  @Override
//  public void disconnect() {
//    Jedis cache = this.getCache();
//    try{
//      cache.disconnect();
//    } finally {
//      this.returnCache(cache);
//    }
//  }
//
//}
