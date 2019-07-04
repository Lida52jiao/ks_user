//package com.yjkj.ks_user.util;
//
//
//import redis.clients.redisUtils.JedisPool;
//import redis.clients.redisUtils.JedisPoolConfig;
//
//public class RedisUtils {
//
//    //Redis服务器IP
//    private static String ADDR = "r-m5e75418c67914c4108.redis.rds.aliyuncs.com";
////    private static String ADDR = "localhost";
//
//    //Redis的端口号
//    private static int PORT = 6379;
//
//    //访问密码,若你的redis服务器没有设置密码，就不需要用密码去连接
//    private static String AUTH = "Asja603494853";
//
//    //可用连接实例的最大数目，默认值为8；
//    private static int MAX_TOTAL = 2000;
//
//    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
//    private static int MAX_IDLE = 1800;
//
//    private static int TIMEOUT = 3000;
//
//    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
//    private static int MAX_WAIT = 10000;
//
//
//    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//    private static boolean TEST_ON_BORROW = true;
//
//    private static JedisPool jedisPool = null;
//
//    /**
//     * 初始化Redis连接池
//     */
//    static {
//        try {
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxTotal(MAX_TOTAL);
//            config.setMaxIdle(MAX_IDLE);
//            config.setMaxWaitMillis(MAX_WAIT);
//            config.setTestOnBorrow(TEST_ON_BORROW);
//            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取Jedis实例
//     * @return
//     */
//    public synchronized static Jedis getJedis() {
//        try {
//            if (jedisPool != null) {
//                Jedis jedis = jedisPool.getResource();
//                return jedis;
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 释放jedis资源
//     * @param jedis
//     */
//    public static void returnResource(final Jedis jedis) {
//        if (jedis != null) {
//            jedisPool.returnResource(jedis);
//        }
//    }
//    public  static  void main(String[] args) {
//        Jedis jedis= RedisUtils.getJedis();
//        redisUtils.set("1", "123");
//        //redisUtils.del("tokenId");
//        System.out.println(redisUtils.get("1"));
//        System.out.println(redisUtils.exists("1"));
//    }
//}