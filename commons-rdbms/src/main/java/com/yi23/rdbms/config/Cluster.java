package com.yi23.rdbms.config;

import java.util.List;

public class Cluster {
	private String id;
	private String clustertype;
	private String strategy;
	private String retrytime;
	private String minimumConnectionCount;
	private String maximumConnectionCount;
	private String trace;
	private String prototypeCount;
	private String houseKeepingTestSql;
	private String simultaneousBuildThrottle;
	private String maximumActiveTime;
	private List<Node> nodes;

	public void setId(String id) {
		this.id = id;
	}

	public void setClustertype(String clustertype) {
		this.clustertype = clustertype;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public void setRetrytime(String retrytime) {
		this.retrytime = retrytime;
	}

	public void setMinimumConnectionCount(String minimumConnectionCount) {
		this.minimumConnectionCount = minimumConnectionCount;
	}

	public void setMaximumConnectionCount(String maximumConnectionCount) {
		this.maximumConnectionCount = maximumConnectionCount;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	public void setPrototypeCount(String prototypeCount) {
		this.prototypeCount = prototypeCount;
	}

	public void setHouseKeepingTestSql(String houseKeepingTestSql) {
		this.houseKeepingTestSql = houseKeepingTestSql;
	}

	public void setSimultaneousBuildThrottle(String simultaneousBuildThrottle) {
		this.simultaneousBuildThrottle = simultaneousBuildThrottle;
	}

	public void setMaximumActiveTime(String maximumActiveTime) {
		this.maximumActiveTime = maximumActiveTime;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public String getId() {
		return this.id;
	}

	public String getClustertype() {
		return this.clustertype;
	}

	public String getStrategy() {
		return this.strategy;
	}

	public String getRetrytime() {
		return this.retrytime;
	}

	public String getMinimumConnectionCount() {
		return this.minimumConnectionCount;
	}

	public String getMaximumConnectionCount() {
		return this.maximumConnectionCount;
	}

	public String getTrace() {
		return this.trace;
	}

	public String getPrototypeCount() {
		return this.prototypeCount;
	}

	public String getHouseKeepingTestSql() {
		return this.houseKeepingTestSql;
	}

	public String getSimultaneousBuildThrottle() {
		return this.simultaneousBuildThrottle;
	}

	public String getMaximumActiveTime() {
		return this.maximumActiveTime;
	}

	public List<Node> getNodes() {
		return this.nodes;
	}
}