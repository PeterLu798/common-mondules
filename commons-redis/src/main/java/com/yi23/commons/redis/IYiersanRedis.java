package com.yi23.commons.redis;

import redis.clients.jedis.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * <p>Description: redis API </p>
 * <p>Company www.yi23.net </p>
 * <p>author lubaijiang </p>
 * <p>date 2018/3/1下午5:49 </P>
 */
public abstract interface IYiersanRedis extends Serializable {

    /**
     *  If the key already exists and is a string, this command appends the provided value at the end of the string.
     * @param key
     * @param value
     * @return Integer reply, specifically the total length of the string after the append operation.
     */
    public abstract Long append(String key, String value);

    /**
     * BLPOP (and BRPOP) is a blocking list pop primitive.
     * @param timeout
     * @param keys
     * @return BLPOP returns a two-elements array via a multi bulk reply in order to return both the unblocking key and the popped value.<br>
     * When a non-zero timeout is specified, and the BLPOP operation timed out, the return value is a nil multi bulk reply. Most client values will return false or nil accordingly to the programming language used.
     */
    public abstract List<String> blpop(int timeout, String... keys);

    /**
     * BLPOP (and BRPOP) is a blocking list pop primitive.
     * @param timeout
     * @param keys
     * @return BLPOP returns a two-elements array via a multi bulk reply in order to return both the unblocking key and the popped value.
    When a non-zero timeout is specified, and the BLPOP operation timed out, the return value is a nil multi bulk reply. Most client values will return false or nil accordingly to the programming language used.
     */
    public abstract List<String> brpop(int timeout, String... keys);

    /**
     * Pop a value from a list, push it to another list and return it; or block until one is available
     * @param source
     * @param destination
     * @param timeout
     * @return
     */
    public abstract String brpoplpush(String source, String destination, int timeout);

    /**
     * Decrement the number stored at key by one.
     * @param key
     * @return Integer reply, this commands will reply with the new value of key after the increment.
     */
    public abstract Long decr(String key);

    /**
     * IDECRBY work just like INCR but instead to decrement by 1 the decrement is integer.
     * @param key
     * @param integer
     * @return Integer reply, this commands will reply with the new value of key after the increment.
     */
    public abstract Long decrBy(String key, long integer);

    /**
     * Remove the specified keys.
     * @param keys
     * @return Integer reply, specifically: an integer greater than 0 if one or more keys were removed 0 if none of the specified key existed
     */
    public abstract Long del(String... keys);

    /**
     *
     * @param string
     * @return
     */
    public abstract String echo(String string);

    /**
     * Test if the specified key exists.
     * @param key
     * @return
     */
    public abstract Boolean exists(String key);

    /**
     * Set a timeout on the specified key.
     * @param key
     * @param seconds
     * @return Integer reply, specifically: 1: the timeout was set. 0: the timeout was not set since the key already has an associated timeout (this may happen only in Redis versions < 2.1.3, Redis >= 2.1.3 will happily update the timeout), or the key does not exist.
     */
    public abstract Long expire(String key, int seconds);

    /**
     * EXPIREAT works exctly like EXPIRE but instead to get the number of seconds representing the Time To Live of the key as a second argument (that is a relative way of specifing the TTL), it takes an absolute one in the form of a UNIX timestamp (Number of seconds elapsed since 1 Gen 1970).
     * @param key
     * @param unixTime
     * @return Integer reply, specifically: 1: the timeout was set. 0: the timeout was not set since the key already has an associated timeout (this may happen only in Redis versions < 2.1.3, Redis >= 2.1.3 will happily update the timeout), or the key does not exist.
     */
    public abstract Long expireAt(String key, long unixTime);

    /**
     * Get the value of the specified key. If the key does not exist the special value 'nil' is returned. If the value stored at key is not a string an error is returned because GET can only handle string values.
     * @param key
     * @return Bulk reply
     */
    public abstract String get(String key);

    /**
     * Returns the bit value at offset in the string value stored at key
     * @param key
     * @param offset
     * @return
     */
    public abstract Boolean getbit(String key, long offset);

    /**
     *
     * @param key
     * @param startOffset
     * @param endOffset
     * @return
     */
    public abstract String getrange(String key, long startOffset, long endOffset);

    /**
     * GETSET is an atomic set this value and return the old value command.
     * @param key
     * @param value
     * @return Bulk reply
     */
    public abstract String getSet(String key, String value);

    /**
     * Remove the specified field from an hash stored at key.
     * @param key
     * @param fields
     * @return If the field was present in the hash it is deleted and 1 is returned, otherwise 0 is returned and no operation is performed.
     */
    public abstract Long hdel(String key, String... fields);

    /**
     * Test for existence of a specified field in a hash.
     * @param key
     * @param field
     * @return
     */
    public abstract Boolean hexists(String key, String field);

    /**
     * If key holds a hash, retrieve the value associated to the specified field.<br>
     If the field is not found or the key does not exist, a special 'nil' value is returned.
     * @param key
     * @param field
     * @return Bulk reply
     */
    public abstract String hget(String key, String field);

    /**
     * Return all the fields and associated values in a hash.
     * @param key
     * @return All the fields and values contained into a hash.
     */
    public abstract Map<String, String> hgetAll(String key);

    /**
     * Increment the number stored at field in the hash at key by value.
     * @param key
     * @param field
     * @param value
     * @return Integer reply The new value at field after the increment operation.
     */
    public abstract Long hincrBy(String key, String field, long value);

    /**
     * Return all the fields in a hash.
     * @param key
     * @return All the fields names contained into a hash.
     */
    public abstract Set<String> hkeys(String key);

    /**
     * Return the number of items in a hash.
     * @param key
     * @return The number of entries (fields) contained in the hash stored at key. If the specified key does not exist, 0 is returned assuming an empty hash.
     */
    public abstract Long hlen(String key);

    /**
     * Retrieve the values associated to the specified fields.
     * @param key
     * @param fields
     * @return Multi Bulk Reply specifically a list of all the values associated with the specified fields, in the same order of the request.
     */
    public abstract List<String> hmget(String key, String... fields);

    /**
     * Set the respective fields to the respective values.
     * @param key
     * @param hash
     * @return Return OK or Exception if hash is empty
     */
    public abstract String hmset(String key, Map<String,String> hash);

    /**
     * Set the specified hash field to the specified value.
     * @param key
     * @param field
     * @param value
     * @return If the field already exists, and the HSET just produced an update of the value, 0 is returned, otherwise if a new field is created 1 is returned.
     */
    public abstract Long hset(String key, String field, String value);

    /**
     * Set the specified hash field to the specified value if the field not exists.
     * @param key
     * @param field
     * @param value
     * @return If the field already exists, 0 is returned, otherwise if a new field is created 1 is returned.
     */
    public abstract Long hsetnx(String key, String field, String value);

    /**
     * Return all the values in a hash.
     * @param key
     * @return All the fields values contained into a hash.
     */
    public abstract List<String> hvals(String key);

    /**
     * Increment the number stored at key by one.
     * @param key
     * @return Integer reply, this commands will reply with the new value of key after the increment.
     */
    public abstract Long incr(String key);

    /**
     * INCRBY work just like INCR but instead to increment by 1 the increment is integer.
     * @param key
     * @param integer
     * @return Integer reply, this commands will reply with the new value of key after the increment.
     */
    public abstract Long incrBy(String key, long integer);

    /**
     * Return the specified element of the list stored at the specified key.
     * @param key
     * @param index
     * @return Bulk reply, specifically the requested element
     */
    public abstract String lindex(String key, long index);

    /**
     *
     * @param key
     * @param where
     * @param pivot
     * @param value
     * @return
     */
    public abstract Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value);

    /**
     * Return the length of the list stored at the specified key.
     * @param key
     * @return The length of the list.
     */
    public abstract Long llen(String key);

    /**
     * Atomically return and remove the first (LPOP) or last (RPOP) element of the list.
     * @param key
     * @return Bulk reply
     */
    public abstract String lpop(String key);

    /**
     * Add the string value to the head (LPUSH) or tail (RPUSH) of the list stored at key.
     * @param key
     * @param strings
     * @return Integer reply, specifically, the number of elements inside the list after the push operation.
     */
    public abstract Long lpush(String key, String... strings);

    /**
     *
     * @param key
     * @param string
     * @return
     */
    public abstract Long lpushx(String key, String string);

    /**
     * Return the specified elements of the list stored at the specified key.
     * @param key
     * @param start
     * @param end
     * @return Multi bulk reply, specifically a list of elements in the specified range.
     */
    public abstract List<String> lrange(String key, long start, long end);

    /**
     * Remove the first count occurrences of the value element from the list.
     * @param key
     * @param count
     * @param value
     * @return Integer Reply, specifically: The number of removed elements if the operation succeeded
     */
    public abstract Long lrem(String key, long count, String value);

    /**
     * Set a new value as the element at index position of the List at key.
     * @param key
     * @param index
     * @param value
     * @return Status code reply
     */
    public abstract String lset(String key, long index, String value);

    /**
     * Trim an existing list so that it will contain only the specified range of elements specified.
     * @param key
     * @param start
     * @param end
     * @return Status code reply
     */
    public abstract String ltrim(String key, long start, long end);

    /**
     * Get the values of all the specified keys.
     * @param keys
     * @return Multi bulk reply
     */
    public abstract List<String> mget(String... keys);

    /**
     * Set the the respective keys to the respective values.
     * @param keysvalues
     * @return Status code reply Basically +OK as MSET can't fail
     */
    public abstract String mset(String... keysvalues);

    /**
     * Set the the respective keys to the respective values.
     * @param keysvalues
     * @return Integer reply, specifically: 1 if the all the keys were set 0 if no key was set (at least one key already existed)
     */
    public abstract Long msetnx(String... keysvalues);

    /**
     * Undo a expire at turning the expire key into a normal key.
     * @param key
     * @return Integer reply, specifically: 1: the key is now persist. 0: the key is not persist (only happens when key not set).
     */
    public abstract Long persist(String key);

    public abstract void psubscribe(JedisPubSub jedisPubSub, String... patterns);

    public abstract Long publish(String channel, String message);

    /**
     * Atomically renames the key oldkey to newkey.
     * @param oldkey
     * @param newkey
     * @return Status code repy
     */
    public abstract String rename(String oldkey, String newkey);

    /**
     * Rename oldkey into newkey but fails if the destination key newkey already exists.
     * @param oldkey
     * @param newkey
     * @return Integer reply, specifically: 1 if the key was renamed 0 if the target key already exist
     */
    public abstract Long renamenx(String oldkey, String newkey);

    /**
     * Atomically return and remove the first (LPOP) or last (RPOP) element of the list.
     * @param key
     * @return Bulk reply
     */
    public abstract String rpop(String key);

    /**
     * Atomically return and remove the last (tail) element of the srckey list, and push the element as the first (head) element of the dstkey list.
     * @param srckey
     * @param dstkey
     * @return Bulk reply
     */
    public abstract String rpoplpush(String srckey, String dstkey);

    /**
     * Add the string value to the head (LPUSH) or tail (RPUSH) of the list stored at key.
     * @param key
     * @param strings
     * @return Integer reply, specifically, the number of elements inside the list after the push operation.
     */
    public abstract Long rpush(String key, String... strings);

    public abstract Long rpushx(String key, String... string);

    /**
     * Add the specified member to the set value stored at key.
     * @param key
     * @param members
     * @return Integer reply, specifically: 1 if the new element was added 0 if the element was already a member of the set
     */
    public abstract Long sadd(String key, String... members);

    /**
     * Return the set cardinality (number of elements).
     * @param key
     * @return
     */
    public abstract Long scard(String key);

    /**
     *
     * @param sha1
     * @return
     */
    public abstract List<Boolean> scriptExists(String key, String... sha1);

    /**
     *
     * @param sha1
     * @return
     */
    public abstract Boolean scriptExists(String sha1, String key);

    /**
     *
     * @param script
     * @return
     */
    public abstract String scriptLoad(String script, String key);

    /**
     * Return the difference between the Set stored at key1 and all the Sets key2, ..., keyN
     * @param keys
     * @return Return the members of a set resulting from the difference between the first set provided and all the successive sets.
     */
    public abstract Set<String> sdiff(String... keys);

    /**
     * This command works exactly like SDIFF but instead of being returned the resulting set is stored in dstkey.
     * @param dstkey
     * @param keys
     * @return Status code reply
     */
    public abstract Long sdiffstore(String dstkey, String... keys);

    /**
     * Select the DB with having the specified zero-based numeric index.
     * @param index
     * @return Status code reply
     */
    public abstract String select(int index);

    /**
     * Set the string value as value of the key.
     * @param key
     * @param value
     * @return Status code reply
     */
    public abstract String set(String key, String value);

    /**
     * Sets or clears the bit at offset in the string value stored at key
     * @param key
     * @param offset
     * @param value
     * @return
     */
    public abstract Boolean setbit(String key, long offset, boolean value);

    /**
     * The command is exactly equivalent to the following group of commands: SET + EXPIRE.
     * @param key
     * @param seconds
     * @param value
     * @return Status code reply
     */
    public abstract String setex(String key, int seconds, String value);

    /**
     * SETNX works exactly like SET with the only difference that if the key already exists no operation is performed.
     * @param key
     * @param value
     * @return Integer reply, specifically: 1 if the key was set 0 if the key was not set
     */
    public abstract Long setnx(String key, String value);

    public abstract Long setrange(String key, long offset, String value);

    /**
     * Return the members of a set resulting from the intersection of all the sets hold at the specified keys.
     * @param keys
     * @return
     */
    public abstract Set<String> sinter(String... keys);

    /**
     * This commnad works exactly like SINTER but instead of being returned the resulting set is sotred as dstkey.
     * @param dstkey
     * @param keys
     * @return Status code reply
     */
    public abstract Long sinterstore(String dstkey, String... keys);

    /**
     * Return 1 if member is a member of the set stored at key, otherwise 0 is returned.
     * @param key
     * @param member
     * @return
     */
    public abstract Boolean sismember(String key, String member);

    /**
     * Return all the members (elements) of the set value stored at key.
     * @param key
     * @return
     */
    public abstract Set<String> smembers(String key);

    /**
     * Move the specifided member from the set at srckey to the set at dstkey.
     * @param srckey
     * @param dstkey
     * @param member
     * @return Integer reply, specifically: 1 if the element was moved 0 if the element was not found on the first set and no operation was performed
     */
    public abstract Long smove(String srckey, String dstkey, String member);

    /**
     * Sort a Set or a List.
     * @param key
     * @return Assuming the Set/List at key contains a list of numbers, the return value will be the list of numbers ordered from the smallest to the biggest number.
     */
    public abstract List<String> sort(String key);

    /**
     * Sort a Set or a List accordingly to the specified parameters.
     * @param key
     * @param sortingParameters
     * @return a list of sorted elements.
     */
    public abstract List<String> sort(String key, SortingParams sortingParameters);

    /**
     * Sort a Set or a List accordingly to the specified parameters and store the result at dstkey.
     * @param key
     * @param sortingParameters
     * @param dstkey
     * @return The number of elements of the list at dstkey.
     */
    public abstract Long sort(String key, SortingParams sortingParameters, String dstkey);

    /**
     * Sort a Set or a List and Store the Result at dstkey.
     * @param key
     * @param dstkey
     * @return The number of elements of the list at dstkey.
     */
    public abstract Long sort(String key, String dstkey);

    /**
     * Remove a random element from a Set returning it as return value.
     * @param key
     * @return
     */
    public abstract String spop(String key);

    /**
     * Return a random element from a Set, without removing the element.
     * @param key
     * @return
     */
    public abstract String srandmember(String key);

    /**
     * Remove the specified member from the set value stored at key.
     * @param key
     * @param members
     * @return
     */
    public abstract Long srem(String key, String... members);

    /**
     *
     * @param key
     * @return
     */
    public abstract Long strlen(String key);

    /**
     * Return a subset of the string from offset start to offset end (both offsets are inclusive).
     * @param key
     * @param start
     * @param end
     * @return Bulk reply
     */
    public abstract String substr(String key, int start, int end);

    /**
     * Return the members of a set resulting from the union of all the sets hold at the specified keys.
     * @param keys
     * @return Multi bulk reply, specifically the list of common elements.
     */
    public abstract Set<String> sunion(String... keys);

    /**
     * This command works exactly like SUNION but instead of being returned the resulting set is stored as dstkey.
     * @param dstkey
     * @param keys
     * @return Status code reply
     */
    public abstract Long sunionstore(String dstkey, String... keys);

    /**
     * The TTL command returns the remaining time to live in seconds of a key that has an EXPIRE set.
     * @param key
     * @return Integer reply, returns the remaining time to live in seconds of a key that has an EXPIRE. If the Key does not exists or does not have an associated expire, -1 is returned.
     */
    public abstract Long ttl(String key);

    /**
     * Return the type of the value stored at key in form of a string.
     * @param key
     * @return
     */
    public abstract String type(String key);

    /**
     * Add the specified member having the specifeid score to the sorted set stored at key.
     * @param key
     * @param score
     * @param member
     * @return Integer reply, specifically: 1 if the new element was added 0 if the element was already a member of the sorted set and the score was updated
     */
    public abstract Long zadd(String key, double score, String member);

    /**
     * Return the sorted set cardinality (number of elements).
     * @param key
     * @return
     */
    public abstract Long zcard(String key);

    /**
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public abstract Long zcount(String key, double min, double max);

    public abstract Long zcount(String key, String min, String max);

    /**
     * If member already exists in the sorted set adds the increment to its score and updates the position of the element in the sorted set accordingly.
     * @param key
     * @param score
     * @param member
     * @return The new score
     */
    public abstract Double zincrby(String key, double score, String member);

    /**
     * Creates a union or intersection of N sorted sets given by keys k1 through kN, and stores it at dstkey.
     * @param dstkey
     * @param sets
     * @return
     */
    public abstract Long zinterstore(String dstkey, String... sets);

    /**
     * Creates a union or intersection of N sorted sets given by keys k1 through kN, and stores it at dstkey.
     * @param dstkey
     * @param params
     * @param sets
     * @return
     */
    public abstract Long zinterstore(String dstkey, ZParams params, String... sets);

    public abstract Set<String> zrange(String key, long start, long end);

    /**
     * Return the all the elements in the sorted set at key with a score between min and max (including elements with score equal to min or max).
     * @param key
     * @param min
     * @param max
     * @return
     */
    public abstract Set<String> zrangeByScore(String key, double min, double max);

    /**
     * Return the all the elements in the sorted set at key with a score between min and max (including elements with score equal to min or max).
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    public abstract Set<String> zrangeByScore(String key, double min, double max, int offset, int count);

    /**
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public abstract Set<String> zrangeByScore(String key, String min, String max);

    public abstract Set<String> zrangeByScore(String key, String min, String max, int offset, int count);

    /**
     * Return the all the elements in the sorted set at key with a score between min and max (including elements with score equal to min or max).
     * @param key
     * @param min
     * @param max
     * @return Multi bulk reply specifically a list of elements in the specified score range.
     */
    public abstract Set<Tuple> zrangeByScoreWithScores(String key, double min, double max);

    /**
     * Return the all the elements in the sorted set at key with a score between min and max (including elements with score equal to min or max).
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return Multi bulk reply specifically a list of elements in the specified score range.
     */
    public abstract Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count);

    /**
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public abstract Set<Tuple> zrangeByScoreWithScores(String key, String min, String max);

    public abstract Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count);

    public abstract Set<Tuple> zrangeWithScores(String key, long start, long end);

    /**
     * Return the rank (or index) or member in the sorted set at key, with scores being ordered from low to high.
     * @param key
     * @param member
     * @return Integer reply or a nil bulk reply, specifically: the rank of the element as an integer reply if the element exists. A nil bulk reply if there is no such element.
     */
    public abstract Long zrank(String key, String member);

    /**
     * Remove the specified member from the sorted set value stored at key.
     * @param key
     * @param members
     * @return Integer reply, specifically: 1 if the new element was removed 0 if the new element was not a member of the set
     */
    public abstract Long zrem(String key, String... members);

    /**
     * Remove all elements in the sorted set at key with rank between start and end.
     * @param key
     * @param start
     * @param end
     * @return
     */
    public abstract Long zremrangeByRank(String key, long start, long end);

    /**
     * Remove all the elements in the sorted set at key with a score between min and max (including elements with score equal to min or max).
     * @param key
     * @param start
     * @param end
     * @return
     */
    public abstract Long zremrangeByScore(String key, double start, double end);

    public abstract Long zremrangeByScore(String key, String start, String end);

    public abstract Set<String> zrevrange(String key, long start, long end);

    public abstract Set<String> zrevrangeByScore(String key, double max, double min);

    public abstract Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count);

    public abstract Set<String> zrevrangeByScore(String key, String max, String min);

    public abstract Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count);

    public abstract Set<Tuple> 	zrevrangeByScoreWithScores(String key, String max, String min);

    public abstract Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count);

    public abstract Set<Tuple> zrevrangeWithScores(String key, long start, long end);

    /**
     * Return the rank (or index) or member in the sorted set at key, with scores being ordered from high to low.
     * @param key
     * @param member
     * @return Integer reply or a nil bulk reply, specifically: the rank of the element as an integer reply if the element exists. A nil bulk reply if there is no such element.
     */
    public abstract Long zrevrank(String key, String member);

    /**
     * Return the score of the specified element of the sorted set at key.
     * @param key
     * @param member
     * @return
     */
    public abstract Double zscore(String key, String member);

    /**
     * Creates a union or intersection of N sorted sets given by keys k1 through kN, and stores it at dstkey.
     * @param dstkey
     * @param sets
     * @return
     */
    public abstract Long zunionstore(String dstkey, String... sets);

    /**
     * Creates a union or intersection of N sorted sets given by keys k1 through kN, and stores it at dstkey.
     * @param dstkey
     * @param params
     * @param sets
     * @return
     */
    public abstract Long zunionstore(String dstkey, ZParams params, String... sets);

}
