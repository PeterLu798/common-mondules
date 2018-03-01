package com.yi23.commons.redis.cluster.jedis;

import com.yi23.commons.redis.IYiersanRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClusterRedis implements IYiersanRedis {

    private static final Logger log = LoggerFactory.getLogger(ClusterRedis.class);
    private static final long serialVersionUID = -7226582938942298022L;
    private JedisCluster jedisCluster;
    private int expireTime;

    public ClusterRedis() {
    }

    public ClusterRedis(JedisCluster jedisCluster, int expireTime) {
        this.jedisCluster = jedisCluster;
        this.expireTime = expireTime;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public Long append(String key, String value) {
        return jedisCluster.append(key, value);
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        return jedisCluster.blpop(timeout, keys);
    }

    @Override
    public List<String> brpop(int timeout, String... keys) {
        return jedisCluster.brpop(timeout, keys);
    }

    @Override
    public String brpoplpush(String source, String destination, int timeout) {
        return jedisCluster.brpoplpush(source, destination, timeout);
    }

    @Override
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    @Override
    public Long decrBy(String key, long integer) {
        return jedisCluster.decrBy(key, integer);
    }

    @Override
    public Long del(String... keys) {
        return jedisCluster.del(keys);
    }

    @Override
    public String echo(String string) {
        return jedisCluster.echo(string);
    }

    @Override
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public Long expireAt(String key, long unixTime) {
        return jedisCluster.expireAt(key, unixTime);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public Boolean getbit(String key, long offset) {
        return jedisCluster.getbit(key, offset);
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        return jedisCluster.getrange(key, startOffset, endOffset);
    }

    @Override
    public String getSet(String key, String value) {
        return jedisCluster.getSet(key, value);
    }

    @Override
    public Long hdel(String key, String... fields) {
        return jedisCluster.hdel(key, fields);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return jedisCluster.hexists(key, field);
    }

    @Override
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return jedisCluster.hgetAll(key);
    }

    @Override
    public Long hincrBy(String key, String field, long value) {
        return jedisCluster.hincrBy(key, field, value);
    }

    @Override
    public Set<String> hkeys(String key) {
        return jedisCluster.hkeys(key);
    }

    @Override
    public Long hlen(String key) {
        return jedisCluster.hlen(key);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return jedisCluster.hmget(key, fields);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return jedisCluster.hmset(key, hash);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return jedisCluster.hsetnx(key,field, value);
    }

    @Override
    public List<String> hvals(String key) {
        return jedisCluster.hvals(key);
    }

    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long incrBy(String key, long integer) {
        return jedisCluster.incrBy(key, integer);
    }

    @Override
    public String lindex(String key, long index) {
        return jedisCluster.lindex(key, index);
    }

    @Override
    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return jedisCluster.linsert(key, where, pivot, value);
    }

    @Override
    public Long llen(String key) {
        return jedisCluster.llen(key);
    }

    @Override
    public String lpop(String key) {
        return jedisCluster.lpop(key);
    }

    @Override
    public Long lpush(String key, String... strings) {
        return jedisCluster.lpush(key, strings);
    }

    @Override
    public Long lpushx(String key, String string) {
        return jedisCluster.lpush(key, string);
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        return jedisCluster.lrange(key, start, end);
    }

    @Override
    public Long lrem(String key, long count, String value) {
        return jedisCluster.lrem(key, count, value);
    }

    @Override
    public String lset(String key, long index, String value) {
        return jedisCluster.lset(key, index, value);
    }

    @Override
    public String ltrim(String key, long start, long end) {
        return jedisCluster.ltrim(key, start, end);
    }

    @Override
    public List<String> mget(String... keys) {
        return jedisCluster.mget(keys);
    }

    @Override
    public String mset(String... keysvalues) {
        return jedisCluster.mset(keysvalues);
    }

    @Override
    public Long msetnx(String... keysvalues) {
        return jedisCluster.msetnx(keysvalues);
    }

    @Override
    public Long persist(String key) {
        return jedisCluster.persist(key);
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
        jedisCluster.psubscribe(jedisPubSub, patterns);
    }

    @Override
    public Long publish(String channel, String message) {
        return jedisCluster.publish(channel,message);
    }

    @Override
    public String rename(String oldkey, String newkey) {
        return jedisCluster.rename(oldkey,newkey);
    }

    @Override
    public Long renamenx(String oldkey, String newkey) {
        return jedisCluster.renamenx(oldkey, newkey);
    }

    @Override
    public String rpop(String key) {
        return jedisCluster.rpop(key);
    }

    @Override
    public String rpoplpush(String srckey, String dstkey) {
        return jedisCluster.rpoplpush(srckey,dstkey);
    }

    @Override
    public Long rpush(String key, String... strings) {
        return jedisCluster.rpush(key, strings);
    }

    @Override
    public Long rpushx(String key, String... string) {
        return jedisCluster.rpushx(key,string);
    }

    @Override
    public Long sadd(String key, String... members) {
        return jedisCluster.sadd(key, members);
    }

    @Override
    public Long scard(String key) {
        return jedisCluster.scard(key);
    }

    @Override
    public List<Boolean> scriptExists(String key, String... sha1) {
        return jedisCluster.scriptExists(key, sha1);
    }

    @Override
    public Boolean scriptExists(String sha1,String key) {
        return jedisCluster.scriptExists(sha1,key);
    }

    @Override
    public String scriptLoad(String script, String key) {
        return jedisCluster.scriptLoad(script, key);
    }

    @Override
    public Set<String> sdiff(String... keys){
        return jedisCluster.sdiff(keys);
    }

    @Override
    public Long sdiffstore(String dstkey, String... keys) {
        return jedisCluster.sdiffstore(dstkey,keys);
    }

    @Override
    public String select(int index) {
        return jedisCluster.select(index);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        return jedisCluster.setbit(key,offset, value);
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return jedisCluster.setex(key, seconds, value);
    }

    @Override
    public Long setnx(String key, String value) {
        return jedisCluster.setnx(key, value);
    }

    @Override
    public Long setrange(String key, long offset, String value) {
        return jedisCluster.setrange(key, offset, value);
    }

    @Override
    public Set<String> sinter(String... keys) {
        return jedisCluster.sinter(keys);
    }

    @Override
    public Long sinterstore(String dstkey, String... keys) {
        return jedisCluster.sinterstore(dstkey,keys);
    }

    @Override
    public Boolean sismember(String key, String member) {
        return jedisCluster.sismember(key, member);
    }

    @Override
    public Set<String> smembers(String key) {
        return jedisCluster.smembers(key);
    }

    @Override
    public Long smove(String srckey, String dstkey, String member) {
        return jedisCluster.smove(srckey,dstkey,member);
    }

    @Override
    public List<String> sort(String key) {
        return jedisCluster.sort(key);
    }

    @Override
    public List<String> sort(String key, SortingParams sortingParameters) {
        return jedisCluster.sort(key, sortingParameters);
    }

    @Override
    public Long sort(String key, SortingParams sortingParameters, String dstkey) {
        return jedisCluster.sort(key, sortingParameters, dstkey);
    }

    @Override
    public Long sort(String key, String dstkey) {
        return jedisCluster.sort(key, dstkey);
    }

    @Override
    public String spop(String key) {
        return jedisCluster.spop(key);
    }

    @Override
    public String srandmember(String key) {
        return jedisCluster.srandmember(key);
    }

    @Override
    public Long srem(String key, String... members) {
        return jedisCluster.srem(key,members);
    }

    @Override
    public Long strlen(String key) {
        return jedisCluster.strlen(key);
    }

    @Override
    public String substr(String key, int start, int end) {
        return jedisCluster.substr(key, start, end);
    }

    @Override
    public Set<String> sunion(String... keys) {
        return jedisCluster.sunion(keys);
    }

    @Override
    public Long sunionstore(String dstkey, String... keys) {
        return jedisCluster.sunionstore(dstkey,keys);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public String type(String key) {
        return jedisCluster.type(key);
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return jedisCluster.zadd(key, score, member);
    }

    @Override
    public Long zcard(String key) {
        return jedisCluster.zcard(key);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return jedisCluster.zcount(key,min,max);
    }

    @Override
    public Long zcount(String key, String min, String max) {
        return jedisCluster.zcount(key, min, max);
    }

    @Override
    public Double zincrby(String key, double score, String member) {
        return jedisCluster.zincrby(key, score, member);
    }

    @Override
    public Long zinterstore(String dstkey, String... sets) {
        return jedisCluster.zinterstore(dstkey, sets);
    }

    @Override
    public Long zinterstore(String dstkey, ZParams params, String... sets) {
        return jedisCluster.zinterstore(dstkey,params, sets);
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        return jedisCluster.zrange(key, start,end);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return jedisCluster.zrangeByScore(key,min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        return jedisCluster.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max) {
        return jedisCluster.zrangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        return jedisCluster.zrangeByScore(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        return jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        return jedisCluster.zrangeWithScores(key, start, end);
    }

    @Override
    public Long zrank(String key, String member) {
        return jedisCluster.zrank(key, member);
    }

    @Override
    public Long zrem(String key, String... members) {
        return jedisCluster.zrem(key, members);
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        return jedisCluster.zremrangeByRank(key, start, end);
    }

    @Override
    public Long zremrangeByScore(String key, double start, double end) {
        return jedisCluster.zremrangeByScore(key, start, end);
    }

    @Override
    public Long zremrangeByScore(String key, String start, String end) {
        return jedisCluster.zremrangeByScore(key, start, end);
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        return jedisCluster.zrevrange(key, start, end);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min) {
        return jedisCluster.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return jedisCluster.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min) {
        return jedisCluster.zrevrangeByScore(key, max, min);
    }

    @Override
    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        return jedisCluster.zrevrangeByScore(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min);
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        return jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        return jedisCluster.zrevrangeWithScores(key, start, end);
    }

    @Override
    public Long zrevrank(String key, String member) {
        return jedisCluster.zrevrank(key, member);
    }

    @Override
    public Double zscore(String key, String member) {
        return jedisCluster.zscore(key, member);
    }

    @Override
    public Long zunionstore(String dstkey, String... sets) {
        return jedisCluster.zunionstore(dstkey, sets);
    }

    @Override
    public Long zunionstore(String dstkey, ZParams params, String... sets) {
        return jedisCluster.zunionstore(dstkey, params, sets);
    }
}
