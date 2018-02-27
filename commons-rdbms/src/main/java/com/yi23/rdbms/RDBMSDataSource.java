package com.yi23.rdbms;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.yi23.rdbms.config.RDBMSConfigHelper;
import com.yi23.rdbms.exception.RDBMSException;

public class RDBMSDataSource implements DataSource {
	private int loginTimeout;
	private transient PrintWriter out;
	private RDBMSManager routingManager = null;
	private String clusterId;
	private RDBMS dbCluster;

	public String getClusterId() {
		return this.clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
		List<RDBMS> clusters = this.routingManager.getClusters();
		for (RDBMS cluster : clusters) {
			if (cluster.getClusterId().equals(clusterId)) {
				this.dbCluster = cluster;
			}
		}
		if (this.dbCluster == null)
			throw new RDBMSException("can not find cluster id=" + clusterId + ",please modify the datasource config");
	}

	public RDBMSDataSource(RDBMSConfigHelper config) {
		this.routingManager = RDBMSManager.getInstance(config);
	}

	public Connection getConnection() throws SQLException {
		return this.dbCluster.getConnection();
	}

	public Connection getConnection(String token, String tokenType) throws SQLException {
		return this.dbCluster.getConnection(token, tokenType);
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		this.loginTimeout = seconds;
	}

	public int getLoginTimeout() throws SQLException {
		return this.loginTimeout;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		this.out = out;
	}

	public PrintWriter getLogWriter() throws SQLException {
		return this.out;
	}

	public Object unwrap(Class iface) throws SQLException {
		return null;
	}

	public boolean isWrapperFor(Class iface) throws SQLException {
		return false;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}
}