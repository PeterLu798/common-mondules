package com.yi23.rdbms;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash<T> {
	private TreeMap<Long, T> virtualNodes;
	private List<T> nodes;
	private final int NODE_NUM = 100;

	public ConsistentHash(List<T> nodes) {
		this.nodes = nodes;
		init();
	}

	private void init() {
		this.virtualNodes = new TreeMap<Long, T>();
		for (int i = 0; i != this.nodes.size(); i++) {
			T shardInfo = this.nodes.get(i);
			for (int n = 0; n < 100; n++)
				this.virtualNodes.put(hash("SHARD-" + i + "-NODE-" + n), shardInfo);
		}
	}

	public T get(String key) {
		SortedMap<Long, T> tail = this.virtualNodes.tailMap(hash(key));
		if (tail.size() == 0) {
			return this.virtualNodes.get(this.virtualNodes.firstKey());
		}
		return tail.get(tail.firstKey());
	}

	private Long hash(String key) {
		ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
		int seed = 305441741;

		ByteOrder byteOrder = buf.order();
		buf.order(ByteOrder.LITTLE_ENDIAN);

		long m = -4132994306676758123L;
		int r = 47;

		long h = seed ^ buf.remaining() * m;

		while (buf.remaining() >= 8) {
			long k = buf.getLong();

			k *= m;
			k ^= k >>> r;
			k *= m;

			h ^= k;
			h *= m;
		}
		if (buf.remaining() > 0) {
			ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

			finish.put(buf).rewind();
			h ^= finish.getLong();
			h *= m;
		}

		h ^= h >>> r;
		h *= m;
		h ^= h >>> r;
		buf.order(byteOrder);
		return Long.valueOf(h);
	}
}
