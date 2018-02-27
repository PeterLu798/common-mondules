package com.yi23.rdbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;

import com.yi23.rdbms.exception.RDBMSException;
import com.yi23.rdbms.node.ClusterNode;
import com.yi23.rdbms.node.ClusterNodeMonitor;
import com.yi23.rdbms.node.ClusterNodeReadOnly;
import com.yi23.rdbms.node.ClusterNodeReadWrite;

public class RDBMS {
	private String clusterId;
	private String clusterType;
	private String clusterStrategy;
	private long retryTime = 200L;
	private Object lock = new Object();
	private List<Properties> nodeProps = new ArrayList();
	private List<String> nodes = new CopyOnWriteArrayList();
	private Map<String, Map<String, Integer>> nodeTrailNumber = new HashMap();
	private ConsistentHash<String> writeHash = null;
	private final ClusterNodeMonitor monitor;
	private final ConcurrentLinkedQueue<String> nodeQueue = new ConcurrentLinkedQueue();
	private final PriorityBlockingQueue<PrioryDS> dsPrioryQueue = new PriorityBlockingQueue();

	public RDBMS(String clusterId, String clusterType, String clusterStrategy, String retryTimeStr) {
		this.clusterId = clusterId;
		if (clusterType != null)
			this.clusterType = clusterType;
		else {
			this.clusterType = "READONLY";
		}
		if (clusterStrategy != null)
			this.clusterStrategy = clusterStrategy;
		else {
			this.clusterStrategy = "MAXPERFORMANCE";
		}
		if (retryTimeStr != null)
			try {
				this.retryTime = Long.parseLong(retryTimeStr);
			} catch (NumberFormatException localNumberFormatException) {
			}
		this.monitor = new ClusterNodeMonitor();
		this.monitor.start(true);
	}

	public String getClusterId() {
		return this.clusterId;
	}

	public String getClusterType() {
		return this.clusterType;
	}

	public String getClusterStrategy() {
		return this.clusterStrategy;
	}

	public void removeNode(String nodeId) {
		if (nodeId == null)
			return;
		if (this.nodes.contains(nodeId))
			this.nodes.remove(nodeId);
	}

	public void addNode(String nodeId) {
		if (nodeId == null)
			return;
		if (!this.nodes.contains(nodeId))
			this.nodes.add(nodeId);
	}

	private ClusterNode createClusterNode(String nodeId, RDBMS cluster) {
		if ("READONLY".equals(this.clusterType))
			return new ClusterNodeReadOnly(nodeId, cluster);
		if ("READWRITE".equals(this.clusterType)) {
			return new ClusterNodeReadWrite(nodeId, cluster);
		}
		return null;
	}

	public void setNodeToLive() {
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			for (Properties prop : this.nodeProps) {
				String url = "proxool." + prop.getProperty("proxool.alias") + ":" + prop.getProperty("proxool.driver")
						+ ":" + prop.getProperty("proxool.url");
				try {
					System.out.println("initial url=" + url);
					String nodeId = ProxoolFacade.registerConnectionPool(url, prop);

					System.out.println("initial connection pool:" + nodeId + " success");
					this.nodes.add(nodeId);
					this.nodeQueue.add(nodeId);

					this.dsPrioryQueue.add(new PrioryDS(nodeId));
					Map trailNumberMap = getTrailNumber(nodeId, prop);

					this.nodeTrailNumber.put(nodeId, trailNumberMap);
					ClusterNode clusterNode = createClusterNode(nodeId, this);

					this.monitor.addClusterNode(clusterNode);
				} catch (ProxoolException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addNodeProperties(Properties prop) {
		this.nodeProps.add(prop);
	}

	public int getConfigNodeSize() {
		return this.nodeProps.size();
	}

	public int getNodeSize() {
		return this.nodes.size();
	}

	public Connection getConnection() throws SQLException {
		if ("READONLY".equals(this.clusterType)) {
			return getReadonlyConnection();
		}
		if ("READWRITE".equals(this.clusterType)) {
			return getReadWriteConnection();
		}
		if ("READMUTIWRITE".equals(this.clusterType)) {
			throw new RDBMSException("the type:" + this.clusterType
					+ " can not support getConnection(),please using getConection(String,String)");
		}

		throw new RDBMSException("the type:" + this.clusterType
				+ " can not support,please set the  clustertype in dbrouting.xml correct");
	}

	public Connection getConnection(String token, String tokenType) throws SQLException {
		if ("READONLY".equals(this.clusterType))
			return getReadonlyConnection();
		if ("READWRITE".equals(this.clusterType))
			return getReadWriteConnection();
		if ("READMUTIWRITE".equals(this.clusterType)) {
			return getReadMutiWriteConnection(token, tokenType);
		}
		throw new RDBMSException("the type:" + this.clusterType
				+ " can not support,please set the  clustertype in dbrouting.xml correct");
	}

	private Connection getReadonlyConnection() throws SQLException {
		if ("RANDOM".equals(this.clusterStrategy)) {
			int nodeSize = this.nodes.size();
			int index = 0;
			if (nodeSize > 0) {
				index = new Random().nextInt(nodeSize);
			}
			String nodeName = (String) this.nodes.get(index);
			return getConnectionFromPool(nodeName, true, false);
		}
		if ("MINCONNECT".equals(this.clusterStrategy)) {
			String minNodeName = "";
			minNodeName = getMinNodeName(null);
			return getConnectionFromPool(minNodeName, true, true);
		}
		if ("MAXPERFORMANCE".equals(this.clusterStrategy)) {
			return getInnerConnection();
		}
		throw new RDBMSException("the stategy:" + this.clusterStrategy
				+ " can not support,please set the  clusterstrategy in dbrouting.xml correct");
	}

	private Connection getInnerConnection() throws SQLException {
		Connection conn = null;
		try {
			String nodeName = (String) this.nodeQueue.poll();
			conn = getConnectionFromPool(nodeName);
			this.nodeQueue.add(nodeName);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (conn == null) {
			try {
				String nodeName = (String) this.nodeQueue.poll();
				conn = getConnectionFromPool(nodeName);
				this.nodeQueue.add(nodeName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return conn;
	}

	private String getRandomNodeName(String excludeNode) {
		int nodeSize = this.nodes.size();
		if (nodeSize == 1)
			return null;
		List copyNodes = new ArrayList(nodeSize - 1);
		copyNodes.addAll(this.nodes);
		if (excludeNode.contains(excludeNode)) {
			copyNodes.remove(excludeNode);
		}
		int index = (int) (Math.random() * (nodeSize - 1));
		String nodeName = (String) copyNodes.get(index);
		return nodeName;
	}

	private String getMinNodeName(String excludeNode) {
		try {
			PrioryDS ds = (PrioryDS) this.dsPrioryQueue.take();
			this.dsPrioryQueue.add(ds);
			String name = ds.getName();
			return name;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Connection getReadWriteConnection() throws SQLException {
		if (this.nodes.size() > 0) {
			return getConnectionFromPool((String) this.nodes.get(0));
		}
		throw new SQLException("the size of cluster nodes is 0,can not get db connection");
	}

	private Connection getReadMutiWriteConnection(String token, String tokenType) throws SQLException {
		if (token == null) {
			throw new SQLException("write token is null,maybe occur error");
		}
		if ("NUMBERWRITE".equals(this.clusterStrategy)) {
			if (token.length() < 2) {
				throw new SQLException("illegal token:" + token + ",maybe occur error");
			}
			String lastTwoNumber = token.substring(token.length() - 2);
			String tokenNode = null;
			for (int i = 0; i < this.nodes.size(); i++) {
				Map trailNumberHash = (Map) this.nodeTrailNumber.get(this.nodes.get(i));

				if ((trailNumberHash == null) || (trailNumberHash.size() <= 0)
						|| (!trailNumberHash.containsKey(lastTwoNumber)))
					continue;
				tokenNode = (String) this.nodes.get(i);
				break;
			}

			if (tokenNode == null) {
				throw new SQLException(
						"can not find the connection node for the token:" + token + ",tokentype:" + tokenType);
			}

			return getConnectionFromPool(tokenNode);
		}
		if ("HASHWRITE".equals(this.clusterStrategy)) {
			if (this.writeHash == null) {
				this.writeHash = new ConsistentHash(this.nodes);
			}
			String hashNode = (String) this.writeHash.get(token);
			return getConnectionFromPool(hashNode);
		}
		throw new RDBMSException("the stategy:" + this.clusterStrategy
				+ " can not support,please set the  clusterstrategy in dbrouting.xml correct");
	}

	private Map<String, Integer> getTrailNumber(String nodeId, Properties prop) {
		Map trailNumberHash = new HashMap();
		String trailNumberStr = prop.getProperty("trailnumber");
		if (trailNumberStr != null) {
			String[] trailStrs = trailNumberStr.split(",");
			for (String trailStr : trailStrs) {
				String[] trailNumbers = trailStr.split("-");
				for (String trailNumber : trailNumbers)
					try {
						int trailInt = Integer.parseInt(trailNumber);
						if ((trailInt >= 0) && (trailInt > 99))
							continue;
						String trailKey = "";
						if (trailInt < 10)
							trailKey = "0" + trailInt;
						else {
							trailKey = String.valueOf(trailInt);
						}
						trailNumberHash.put(trailKey, Integer.valueOf(trailInt));
					} catch (NumberFormatException localNumberFormatException) {
					}
			}
		}
		return trailNumberHash;
	}

	public Properties getNodeProperties(String nodeId) {
		for (Properties prop : this.nodeProps) {
			if (nodeId.equals(prop.getProperty("proxool.alias"))) {
				return prop;
			}
		}
		return null;
	}

	private void printGetConnectionInfo(String currentNode, int times) {
	}

	private Connection getConnectionFromPool(String nodeId) throws SQLException {
		return getConnectionFromPool(nodeId, false, false);
	}

	private Connection getConnectionFromPool(String nodeId, boolean isRertyNext, boolean isLeast) throws SQLException {
		if (nodeId == null)
			throw new SQLException("can not find the node id=" + nodeId);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("proxool." + nodeId);
			printGetConnectionInfo(nodeId, 1);
		} catch (SQLException ex) {
			try {
				Thread.currentThread();
				Thread.sleep(this.retryTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection("proxool." + nodeId);
				printGetConnectionInfo(nodeId, 2);
			} catch (SQLException ex2) {
				if (isRertyNext) {
					String nextRetryNode = null;
					if (isLeast)
						synchronized (this.lock) {
							nextRetryNode = getMinNodeName(nodeId);
						}
					else {
						nextRetryNode = getRandomNodeName(nodeId);
					}
					if (nextRetryNode == null) {
						System.out.println("can not find nextRetryNode for node:" + nodeId);

						throw ex2;
					}
					try {
						conn = DriverManager.getConnection("proxool." + nextRetryNode);

						printGetConnectionInfo(nextRetryNode, 3);
					} catch (SQLException ex3) {
						try {
							Thread.currentThread();
							Thread.sleep(this.retryTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						conn = DriverManager.getConnection("proxool." + nextRetryNode);

						printGetConnectionInfo(nextRetryNode, 4);
					}
				} else {
					throw ex2;
				}
			}
		}
		return conn;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("clusterId:");
		sb.append(this.clusterId);
		sb.append(",");
		sb.append("clusterType:");
		sb.append(this.clusterType);
		sb.append(",");
		sb.append("clusterStrategy:");
		sb.append(this.clusterStrategy);
		sb.append(",nodes:");
		for (Properties p : this.nodeProps) {
			sb.append(p.toString());
		}
		return sb.toString();
	}

	class PrioryDS implements Comparable<PrioryDS> {
		String nodeName;

		public PrioryDS(String nodeId) {
			this.nodeName = nodeId;
		}

		public String getName() {
			return this.nodeName;
		}

		public int compareTo(PrioryDS arg0) {
			try {
				return ProxoolFacade.getSnapshot(this.nodeName).getAvailableConnectionCount() > ProxoolFacade
						.getSnapshot(arg0.getName()).getAvailableConnectionCount() ? 0 : 1;
			} catch (ProxoolException e) {
				e.printStackTrace();
			}
			return 0;
		}

		public String toString() {
			try {
				return this.nodeName + "." + ProxoolFacade.getSnapshot(this.nodeName).getAvailableConnectionCount();
			} catch (ProxoolException e) {
				e.printStackTrace();
			}
			return this.nodeName + ".0";
		}
	}
}
