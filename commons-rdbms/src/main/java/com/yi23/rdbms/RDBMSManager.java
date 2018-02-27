package com.yi23.rdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yi23.rdbms.config.Cluster;
import com.yi23.rdbms.config.Node;
import com.yi23.rdbms.config.RDBMSConfigHelper;
import com.yi23.rdbms.exception.RDBMSException;

public final class RDBMSManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(RDBMSManager.class);
	private static List<RDBMS> cs = null;
	private static Set<String> clusterIds = new HashSet<String>();
	private static RDBMSManager rdbmsManager = null;

	private RDBMSManager(RDBMSConfigHelper rc) {
		initDbRouting(rc);
	}

	public static RDBMSManager getInstance(RDBMSConfigHelper rc) {
		if (rdbmsManager == null) {
			synchronized (RDBMSManager.class) {
				if (rdbmsManager == null) {
					rdbmsManager = new RDBMSManager(rc);
				}
			}
		}
		return rdbmsManager;
	}

	private void initDbRouting(RDBMSConfigHelper rc) {
		List<Cluster> clusterConfigs = rc.getClusters();
		if ((clusterConfigs != null) && (!clusterConfigs.isEmpty())) {
			cs = new ArrayList<RDBMS>(clusterConfigs.size());
			for (Cluster clusterConfig : clusterConfigs) {
				String cId = clusterConfig.getId();
				String clusterType = clusterConfig.getClustertype();
				if ("READMUTIWRITE".equals(clusterType)) {
					continue;
				}
				String cly = clusterConfig.getStrategy();
				String minCon = clusterConfig.getMinimumConnectionCount();
				String maxCon = clusterConfig.getMaximumConnectionCount();
				String retrytime = clusterConfig.getRetrytime();
				RDBMS cluster = new RDBMS(cId, clusterType, cly, retrytime);
				String trace = clusterConfig.getTrace();
				String prototypeCount = clusterConfig.getPrototypeCount();
				String houseKeepingTestSql = clusterConfig.getHouseKeepingTestSql();
				String simultaneousBuildThrottle = clusterConfig.getSimultaneousBuildThrottle();
				String maximumActiveTime = clusterConfig.getMaximumActiveTime();
				List<Node> nodes = clusterConfig.getNodes();
				if ((nodes != null) && (nodes.size() != 0)) {
					if (minCon == null) {
						minCon = String.valueOf(10 * nodes.size());
					}
					if (maxCon == null) {
						maxCon = String.valueOf(50 * nodes.size());
					}
					HashMap<String, String> minHash = calculateConnection(minCon, nodes.size());
					HashMap<String, String> maxHash = calculateConnection(maxCon, nodes.size());
					for (int nodeIndex = 0; nodeIndex < nodes.size(); nodeIndex++) {
						Node node = (Node) nodes.get(nodeIndex);
						String nodeId = node.getId();
						String driver = node.getDriver();
						String url = node.getUrl();
						String user = node.getUser();
						String password = node.getPassword();
						String trailnumber = node.getTrailnumber();
						Properties nodeProp = new Properties();
						nodeProp.setProperty("proxool.alias", nodeId);
						if (trace == null) {
							trace = "true";
						}
						nodeProp.setProperty("proxool.trace", trace);
						if (prototypeCount == null) {
							prototypeCount = "10";
						}
						nodeProp.setProperty("proxool.prototype-count", prototypeCount);
						if (houseKeepingTestSql == null) {
							houseKeepingTestSql = "select 1 from dual";
						}
						nodeProp.setProperty("proxool.house-keeping-test-sql", houseKeepingTestSql);
						if (simultaneousBuildThrottle == null) {
							simultaneousBuildThrottle = "100";
						}
						nodeProp.setProperty("proxool.simultaneous-build-throttle", simultaneousBuildThrottle);
						if (maximumActiveTime == null) {
							maximumActiveTime = "600000";
						}
						nodeProp.setProperty("proxool.maximum-active-time", maximumActiveTime);
						if ((driver == null) || (url == null) || (user == null) || (password == null)) {
							throw new RDBMSException("the paramater of driver[" + driver + "],url=[" + url + "],user=["
									+ user + "],password=[" + password + "] can not be null");
						}
						nodeProp.setProperty("proxool.driver", driver);
						nodeProp.setProperty("proxool.url", url);
						nodeProp.setProperty("user", user);
						nodeProp.setProperty("password", password);
						nodeProp.setProperty("proxool.minimum-connection-count",
								(String) minHash.get(String.valueOf(nodeIndex)));
						nodeProp.setProperty("proxool.maximum-connection-count",
								(String) maxHash.get(String.valueOf(nodeIndex)));
						if (trailnumber != null) {
							nodeProp.setProperty("trailnumber", trailnumber);
						}
						cluster.addNodeProperties(nodeProp);
					}
					LOGGER.debug(cluster.toString());
					cs.add(cluster);
					clusterIds.add(cId);
				}
			}
		} else {
			throw new RDBMSException("未读取到数据库集群配置信息，请检查数据库集群配置");
		}
		for (RDBMS cluster : cs) {
			LOGGER.info(
					"\n▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n▓    衣二三数据库集群机制            \t\t▓\n▓                               ▓\n▓       初始化集群开始        \t\t▓\n▓                               ▓\n▓      ^_^ ^_^ ^_^ ^_^ ^_^      ▓\n▓                               ▓\n▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

			LOGGER.info("^_^==^_^==^_^==^_^==^_^==初始化集群开始：" + cluster.getClusterId()
					+ "==^_^==^_^==^_^==^_^==^_^==^_^==^_^==^_^");
			cluster.setNodeToLive();
			LOGGER.info("^_^==^_^==^_^==^_^==^_^==初始化集群完毕：" + cluster.getClusterId()
					+ "==^_^==^_^==^_^==^_^==^_^==^_^==^_^==^_^");
			LOGGER.info(
					"\n▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n▓    衣二三数据库集群机制      \t\t▓\n▓                               ▓\n▓       初始化集群完毕          \t\t▓\n▓                               ▓\n▓      ^_^ ^_^ ^_^ ^_^ ^_^      ▓\n▓                               ▓\n▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
		}
	}

	private static HashMap<String, String> calculateConnection(String curCon, int nodeSize) {
		HashMap<String, String> hash = new HashMap<String, String>();
		int intCon = Integer.parseInt(curCon);
		int avgCon = intCon / nodeSize;
		int yuCon = intCon - avgCon * nodeSize;
		if (avgCon == 0) {
			for (int i = 0; i < nodeSize; i++) {
				hash.put(String.valueOf(i), "1");
			}
			return hash;
		}
		if (yuCon == 0) {
			for (int i = 0; i < nodeSize; i++) {
				hash.put(String.valueOf(i), String.valueOf(avgCon));
			}
			return hash;
		}
		for (int i = 0; i < yuCon; i++) {
			hash.put(String.valueOf(i), String.valueOf(avgCon + i + 1));
		}
		for (int i = yuCon; i < nodeSize; i++) {
			hash.put(String.valueOf(i), String.valueOf(avgCon));
		}
		return hash;
	}

	public List<RDBMS> getClusters() {
		return cs;
	}

	public Set<String> getClusterIds() {
		return Collections.unmodifiableSet(clusterIds);
	}

	@SuppressWarnings("unused")
	private byte[] stringToByte(String str) {
		str = str.replace("A", "D").replace("B", "D").replace("C", "D").replace("E", "-").replace("F", "-");
		String[] keys = str.split("D");
		byte[] b = new byte[keys.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = Byte.valueOf(keys[i]).byteValue();
		}
		return b;
	}
}
