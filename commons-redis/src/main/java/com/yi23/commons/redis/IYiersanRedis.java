package com.yi23.commons.redis;

import redis.clients.jedis.*;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract interface IYiersanRedis extends Serializable {

    public abstract Long msetnx(String[] paramArrayOfString);

    public abstract Long move(String paramString, int paramInt);

    public abstract String rename(String paramString1, String paramString2);

    public abstract String set(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong);

    public abstract String set(String paramString1, String paramString2);

    public abstract Long decr(String paramString);

    public abstract Set<String> keys(String paramString);

    public abstract Long expire(String paramString, int paramInt);

    public abstract Long renamenx(String paramString1, String paramString2);

    public abstract String rename(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Long expireAt(String paramString, long paramLong);

    public abstract Long ttl(String paramString);

    public abstract Long persist(String paramString);

    public abstract Long del(String[] paramArrayOfString);

    public abstract Long del(byte[][] paramArrayOfByte);

    public abstract Boolean exists(String paramString);

    public abstract List<String> sort(String paramString);

    public abstract List<String> sort(String paramString, SortingParams paramSortingParams);

    public abstract String type(String paramString);

    public abstract Long sadd(String paramString, String[] paramArrayOfString);

    public abstract Long sadd(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract Long scard(String paramString);

    public abstract Set<String> sdiff(String[] paramArrayOfString);

    public abstract Long sdiffstore(String paramString, String[] paramArrayOfString);

    public abstract Set<String> sinter(String[] paramArrayOfString);

    public abstract Long sinterstore(String paramString, String[] paramArrayOfString);

    public abstract Boolean sismember(String paramString1, String paramString2);

    public abstract Set<String> smembers(String paramString);

    public abstract Set<byte[]> smembers(byte[] paramArrayOfByte);

    public abstract Long smove(String paramString1, String paramString2, String paramString3);

    public abstract String spop(String paramString);

    public abstract Long srem(String paramString, String[] paramArrayOfString);

    public abstract Set<String> sunion(String[] paramArrayOfString);

    public abstract Long sunionstore(String paramString, String[] paramArrayOfString);

    public abstract String srandmember(String paramString);

    public abstract Long zadd(String paramString1, double paramDouble, String paramString2);

    public abstract Long zadd(String paramString, Map<String, Double> paramMap);

    public abstract Long zcard(String paramString);

    public abstract Long zcount(String paramString, double paramDouble1, double paramDouble2);

    public abstract Double zincrby(String paramString1, double paramDouble, String paramString2);

    public abstract Set<String> zrange(String paramString, long paramLong1, long paramLong2);

    public abstract Set<String> zrangeByScore(String paramString, double paramDouble1, double paramDouble2);

    public abstract Long zrank(String paramString1, String paramString2);

    public abstract Long zrevrank(String paramString1, String paramString2);

    public abstract Long zrem(String paramString, String[] paramArrayOfString);

    public abstract Long del(String paramString);

    public abstract Long zremrangeByRank(String paramString, long paramLong1, long paramLong2);

    public abstract Long zremrangeByScore(String paramString, double paramDouble1, double paramDouble2);

    public abstract Set<String> zrevrange(String paramString, long paramLong1, long paramLong2);

    public abstract Double zscore(String paramString1, String paramString2);

    public abstract Long hdel(String paramString, String[] paramArrayOfString);

    public abstract Boolean hexists(String paramString1, String paramString2);

    public abstract String hget(String paramString1, String paramString2);

    public abstract byte[] hget(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Map<String, String> hgetAll(String paramString);

    public abstract Long hset(String paramString1, String paramString2, String paramString3);

    public abstract Long hset(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Long hsetnx(String paramString1, String paramString2, String paramString3);

    public abstract List<String> hvals(String paramString);

    public abstract Long hincrBy(String paramString1, String paramString2, long paramLong);

    public abstract Set<String> hkeys(String paramString);

    public abstract Long hlen(String paramString);

    public abstract List<String> hmget(String paramString, String[] paramArrayOfString);

    public abstract List<byte[]> hmget(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract String hmset(String paramString, Map<String, String> paramMap);

    public abstract String hmset(byte[] paramArrayOfByte, Map<byte[], byte[]> paramMap);

    public abstract String get(String paramString);

    public abstract byte[] get(byte[] paramArrayOfByte);

    public abstract String setex(String paramString1, int paramInt, String paramString2);

    public abstract String setex(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2);

    public abstract Long setnx(String paramString1, String paramString2);

    public abstract String set(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Long setrange(String paramString1, long paramLong, String paramString2);

    public abstract Long append(String paramString1, String paramString2);

    public abstract Long decrBy(String paramString, long paramLong);

    public abstract Long incrBy(String paramString, long paramLong);

    public abstract String getrange(String paramString, long paramLong1, long paramLong2);

    public abstract String getSet(String paramString1, String paramString2);

    public abstract List<String> mget(String[] paramArrayOfString);

    public abstract String mset(String[] paramArrayOfString);

    public abstract Long strlen(String paramString);

    public abstract Long llen(byte[] paramArrayOfByte);

    public abstract String lset(byte[] paramArrayOfByte1, long paramLong, byte[] paramArrayOfByte2);

    public abstract Long linsert(byte[] paramArrayOfByte1, BinaryClient.LIST_POSITION paramLIST_POSITION, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract byte[] lindex(byte[] paramArrayOfByte, long paramLong);

    public abstract byte[] lpop(byte[] paramArrayOfByte);

    public abstract String rpop(String paramString);

    public abstract Long rpush(String paramString, String[] paramArrayOfString);

    public abstract Long rpush(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract Long lpush(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract List<String> lrange(String paramString, long paramLong1, long paramLong2);

    public abstract List<byte[]> lrange(byte[] paramArrayOfByte, long paramLong1, long paramLong2);

    public abstract Long lrem(byte[] paramArrayOfByte1, long paramLong, byte[] paramArrayOfByte2);

    public abstract String ltrim(byte[] paramArrayOfByte, long paramLong1, long paramLong2);

    public abstract Double incrByFloat(String paramString, double paramDouble);

    public abstract Long incr(String paramString);

    public abstract Double hincrByFloat(String paramString1, String paramString2, double paramDouble);

    public abstract Long lpush(String paramString, String[] paramArrayOfString);

    public abstract Long llen(String paramString);

    public abstract String ltrim(String paramString, long paramLong1, long paramLong2);

    public abstract String lindex(String paramString, long paramLong);

    public abstract String lset(String paramString1, long paramLong, String paramString2);

    public abstract Long lrem(String paramString1, long paramLong, String paramString2);

    public abstract String lpop(String paramString);

    public abstract String rpoplpush(String paramString1, String paramString2);

    public abstract Set<String> spop(String paramString, long paramLong);

    public abstract List<String> srandmember(String paramString, int paramInt);

    public abstract Long zadd(String paramString1, double paramDouble, String paramString2, ZAddParams paramZAddParams);

    public abstract Long zadd(String paramString, Map<String, Double> paramMap, ZAddParams paramZAddParams);

    public abstract Double zincrby(String paramString1, double paramDouble, String paramString2, ZIncrByParams paramZIncrByParams);

    public abstract Set<Tuple> zrangeWithScores(String paramString, long paramLong1, long paramLong2);

    public abstract Set<Tuple> zrevrangeWithScores(String paramString, long paramLong1, long paramLong2);

    public abstract List<String> blpop(int paramInt, String[] paramArrayOfString);

    public abstract Long sort(String paramString1, SortingParams paramSortingParams, String paramString2);

    public abstract Long sort(String paramString1, String paramString2);

    public abstract List<String> brpop(int paramInt, String[] paramArrayOfString);

    public abstract Long zcount(String paramString1, String paramString2, String paramString3);

    public abstract Set<String> zrangeByScore(String paramString1, String paramString2, String paramString3);

    public abstract Set<String> zrangeByScore(String paramString, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2);

    public abstract Set<String> zrangeByScore(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

    public abstract Set<Tuple> zrangeByScoreWithScores(String paramString, double paramDouble1, double paramDouble2);

    public abstract Set<Tuple> zrangeByScoreWithScores(String paramString1, String paramString2, String paramString3);

    public abstract Set<Tuple> zrangeByScoreWithScores(String paramString, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2);

    public abstract Set<Tuple> zrangeByScoreWithScores(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

    public abstract Set<String> zrevrangeByScore(String paramString, double paramDouble1, double paramDouble2);

    public abstract Set<String> zrevrangeByScore(String paramString1, String paramString2, String paramString3);

    public abstract Set<String> zrevrangeByScore(String paramString, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(String paramString, double paramDouble1, double paramDouble2);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(String paramString, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

    public abstract Set<String> zrevrangeByScore(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(String paramString1, String paramString2, String paramString3);

    public abstract Long zunionstore(String paramString, ZParams paramZParams, String[] paramArrayOfString);

    public abstract Long zunionstore(String paramString, String[] paramArrayOfString);

    public abstract Long zremrangeByScore(String paramString1, String paramString2, String paramString3);

    public abstract Long zinterstore(String paramString, String[] paramArrayOfString);

    public abstract Long zinterstore(String paramString, ZParams paramZParams, String[] paramArrayOfString);

    public abstract Long zlexcount(String paramString1, String paramString2, String paramString3);

    public abstract Set<String> zrangeByLex(String paramString1, String paramString2, String paramString3);

    public abstract Set<String> zrevrangeByLex(String paramString1, String paramString2, String paramString3);

    public abstract Set<String> zrangeByLex(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

    public abstract Set<String> zrevrangeByLex(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

    public abstract Long zremrangeByLex(String paramString1, String paramString2, String paramString3);

    public abstract Long lpushx(String paramString, String[] paramArrayOfString);

    public abstract Long pexpireAt(String paramString, long paramLong);

    public abstract Long rpushx(String paramString, String[] paramArrayOfString);

    public abstract Long linsert(String paramString1, BinaryClient.LIST_POSITION paramLIST_POSITION, String paramString2, String paramString3);

    public abstract String brpoplpush(String paramString1, String paramString2, int paramInt);

    public abstract Long bitpos(String paramString, boolean paramBoolean);

    public abstract Long bitpos(String paramString, boolean paramBoolean, BitPosParams paramBitPosParams);

    public abstract Long bitcount(String paramString);

    public abstract Long bitcount(String paramString, long paramLong1, long paramLong2);

    public abstract Long bitop(BitOP paramBitOP, String paramString, String[] paramArrayOfString);

    public abstract Long pexpire(String paramString, long paramLong);

    public abstract Long pttl(String paramString);

    public abstract String psetex(String paramString1, long paramLong, String paramString2);

    public abstract String set(String paramString1, String paramString2, String paramString3);

    public abstract String set(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt);

    public abstract ScanResult<Map.Entry<String, String>> hscan(String paramString, int paramInt);

    public abstract ScanResult<Map.Entry<String, String>> hscan(String paramString, int paramInt, ScanParams paramScanParams);

    public abstract ScanResult<String> sscan(String paramString, int paramInt);

    public abstract ScanResult<String> sscan(String paramString, int paramInt, ScanParams paramScanParams);

    public abstract ScanResult<Map.Entry<String, String>> hscan(String paramString1, String paramString2);

    public abstract ScanResult<Map.Entry<String, String>> hscan(String paramString1, String paramString2, ScanParams paramScanParams);

    public abstract ScanResult<String> sscan(String paramString1, String paramString2);

    public abstract ScanResult<String> sscan(String paramString1, String paramString2, ScanParams paramScanParams);

    public abstract ScanResult<Tuple> zscan(String paramString1, String paramString2);

    public abstract ScanResult<Tuple> zscan(String paramString1, String paramString2, ScanParams paramScanParams);

    public abstract Long pfadd(String paramString, String[] paramArrayOfString);

    public abstract long pfcount(String paramString);

    public abstract long pfcount(String[] paramArrayOfString);

    public abstract String pfmerge(String paramString, String[] paramArrayOfString);

    public abstract List<String> blpop(int paramInt, String paramString);

    public abstract List<String> brpop(int paramInt, String paramString);

    public abstract String set(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, long paramLong);

    public abstract Long exists(byte[][] paramArrayOfByte);

    public abstract Boolean exists(byte[] paramArrayOfByte);

    public abstract Long del(byte[] paramArrayOfByte);

    public abstract String type(byte[] paramArrayOfByte);

    public abstract Long renamenx(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Long expire(byte[] paramArrayOfByte, int paramInt);

    public abstract Long expireAt(byte[] paramArrayOfByte, long paramLong);

    public abstract Long ttl(byte[] paramArrayOfByte);

    public abstract Long move(byte[] paramArrayOfByte, int paramInt);

    public abstract byte[] getSet(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract List<byte[]> mget(byte[][] paramArrayOfByte);

    public abstract Long setnx(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract String mset(byte[][] paramArrayOfByte);

    public abstract Long msetnx(byte[][] paramArrayOfByte);

    public abstract Long decrBy(byte[] paramArrayOfByte, long paramLong);

    public abstract Long decr(byte[] paramArrayOfByte);

    public abstract Long incrBy(byte[] paramArrayOfByte, long paramLong);

    public abstract Double incrByFloat(byte[] paramArrayOfByte, double paramDouble);

    public abstract Long incr(byte[] paramArrayOfByte);

    public abstract Long hsetnx(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Long hincrBy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong);

    public abstract Double hincrByFloat(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, double paramDouble);

    public abstract Boolean hexists(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Long hdel(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract Long hlen(byte[] paramArrayOfByte);

    public abstract Set<byte[]> hkeys(byte[] paramArrayOfByte);

    public abstract List<byte[]> hvals(byte[] paramArrayOfByte);

    public abstract Map<byte[], byte[]> hgetAll(byte[] paramArrayOfByte);

    public abstract byte[] rpop(byte[] paramArrayOfByte);

    public abstract byte[] rpoplpush(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Long srem(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract byte[] spop(byte[] paramArrayOfByte);

    public abstract Set<byte[]> spop(byte[] paramArrayOfByte, long paramLong);

    public abstract Long smove(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Long scard(byte[] paramArrayOfByte);

    public abstract Boolean sismember(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Set<byte[]> sinter(byte[][] paramArrayOfByte);

    public abstract Long sinterstore(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract Set<byte[]> sdiff(byte[][] paramArrayOfByte);

    public abstract Long sdiffstore(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract byte[] srandmember(byte[] paramArrayOfByte);

    public abstract List<byte[]> srandmember(byte[] paramArrayOfByte, int paramInt);

    public abstract Long zadd(byte[] paramArrayOfByte1, double paramDouble, byte[] paramArrayOfByte2);

    public abstract Long zadd(byte[] paramArrayOfByte1, double paramDouble, byte[] paramArrayOfByte2, ZAddParams paramZAddParams);

    public abstract Long zadd(byte[] paramArrayOfByte, Map<byte[], Double> paramMap);

    public abstract Long zadd(byte[] paramArrayOfByte, Map<byte[], Double> paramMap, ZAddParams paramZAddParams);

    public abstract Set<byte[]> zrange(byte[] paramArrayOfByte, long paramLong1, long paramLong2);

    public abstract Double zincrby(byte[] paramArrayOfByte1, double paramDouble, byte[] paramArrayOfByte2);

    public abstract Double zincrby(byte[] paramArrayOfByte1, double paramDouble, byte[] paramArrayOfByte2, ZIncrByParams paramZIncrByParams);

    public abstract Long zrank(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Long zrevrank(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Set<byte[]> zrevrange(byte[] paramArrayOfByte, long paramLong1, long paramLong2);

    public abstract Set<Tuple> zrangeWithScores(byte[] paramArrayOfByte, long paramLong1, long paramLong2);

    public abstract Set<Tuple> zrevrangeWithScores(byte[] paramArrayOfByte, long paramLong1, long paramLong2);

    public abstract Long zcard(byte[] paramArrayOfByte);

    public abstract Double zscore(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract List<byte[]> sort(byte[] paramArrayOfByte);

    public abstract Long sort(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Long zcount(byte[] paramArrayOfByte, double paramDouble1, double paramDouble2);

    public abstract Long zcount(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Set<byte[]> zrangeByScore(byte[] paramArrayOfByte, double paramDouble1, double paramDouble2);

    public abstract Set<byte[]> zrangeByScore(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Set<byte[]> zrangeByScore(byte[] paramArrayOfByte, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2);

    public abstract Set<byte[]> zrangeByScore(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2);

    public abstract Set<Tuple> zrangeByScoreWithScores(byte[] paramArrayOfByte, double paramDouble1, double paramDouble2);

    public abstract Set<Tuple> zrangeByScoreWithScores(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Set<Tuple> zrangeByScoreWithScores(byte[] paramArrayOfByte, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2);

    public abstract Set<Tuple> zrangeByScoreWithScores(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2);

    public abstract Set<byte[]> zrevrangeByScore(byte[] paramArrayOfByte, double paramDouble1, double paramDouble2);

    public abstract Set<byte[]> zrevrangeByScore(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Set<byte[]> zrevrangeByScore(byte[] paramArrayOfByte, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2);

    public abstract Set<byte[]> zrevrangeByScore(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(byte[] paramArrayOfByte, double paramDouble1, double paramDouble2);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(byte[] paramArrayOfByte, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2);

    public abstract Long zremrangeByRank(byte[] paramArrayOfByte, long paramLong1, long paramLong2);

    public abstract Long zremrangeByScore(byte[] paramArrayOfByte, double paramDouble1, double paramDouble2);

    public abstract Long zremrangeByScore(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Long zunionstore(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract Long zunionstore(byte[] paramArrayOfByte, ZParams paramZParams, byte[][] paramArrayOfByte1);

    public abstract Long zinterstore(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract Long zinterstore(byte[] paramArrayOfByte, ZParams paramZParams, byte[][] paramArrayOfByte1);

    public abstract Long zlexcount(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Set<byte[]> zrangeByLex(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Set<byte[]> zrangeByLex(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2);

    public abstract Set<byte[]> zrevrangeByLex(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Set<byte[]> zrevrangeByLex(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2);

    public abstract Long zremrangeByLex(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Long strlen(byte[] paramArrayOfByte);

    public abstract Long persist(byte[] paramArrayOfByte);

    public abstract Long rpushx(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract Boolean setbit(byte[] paramArrayOfByte, long paramLong, boolean paramBoolean);

    public abstract Boolean setbit(byte[] paramArrayOfByte1, long paramLong, byte[] paramArrayOfByte2);

    public abstract Long setrange(byte[] paramArrayOfByte1, long paramLong, byte[] paramArrayOfByte2);

    public abstract byte[] getrange(byte[] paramArrayOfByte, long paramLong1, long paramLong2);

    public abstract Long pexpire(byte[] paramArrayOfByte, long paramLong);

    public abstract Long pexpireAt(byte[] paramArrayOfByte, long paramLong);

    public abstract ScanResult<Tuple> zscan(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, ScanParams paramScanParams);

    public abstract ScanResult<Tuple> zscan(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract ScanResult<byte[]> sscan(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, ScanParams paramScanParams);

    public abstract ScanResult<byte[]> sscan(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract ScanResult<Map.Entry<byte[], byte[]>> hscan(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, ScanParams paramScanParams);

    public abstract ScanResult<Map.Entry<byte[], byte[]>> hscan(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Long pfcount(byte[][] paramArrayOfByte);

    public abstract String pfmerge(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract long pfcount(byte[] paramArrayOfByte);

    public abstract Long pfadd(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract String set(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, int paramInt);

    public abstract String set(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3);

    public abstract Long pttl(byte[] paramArrayOfByte);

    public abstract List<byte[]> sort(byte[] paramArrayOfByte, SortingParams paramSortingParams);

    public abstract Long sort(byte[] paramArrayOfByte1, SortingParams paramSortingParams, byte[] paramArrayOfByte2);

    public abstract Long append(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

    public abstract Set<byte[]> sunion(byte[][] paramArrayOfByte);

    public abstract Long sunionstore(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract Long zrem(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract List<byte[]> blpop(int paramInt, byte[][] paramArrayOfByte);

    public abstract List<byte[]> brpop(int paramInt, byte[][] paramArrayOfByte);

    public abstract Long lpushx(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);

    public abstract byte[] brpoplpush(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt);

    public abstract Long bitcount(byte[] paramArrayOfByte);

    public abstract Long bitcount(byte[] paramArrayOfByte, long paramLong1, long paramLong2);

    public abstract Long bitop(BitOP paramBitOP, byte[] paramArrayOfByte, byte[][] paramArrayOfByte1);
}
