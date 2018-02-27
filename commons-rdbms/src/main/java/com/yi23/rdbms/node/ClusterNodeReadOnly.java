package com.yi23.rdbms.node;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.yi23.rdbms.RDBMS;

public class ClusterNodeReadOnly implements ClusterNode {
	private String nodeId;
	private RDBMS cluster;

	public ClusterNodeReadOnly(String nodeId, RDBMS cluster) {
		this.nodeId = nodeId;
		this.cluster = cluster;
	}

	public boolean checkStatus() {
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		int count = -1;
		String testSql = null;
		try {
			con = DriverManager.getConnection("proxool." + this.nodeId);
			st = con.createStatement();
			Properties prop = this.cluster.getNodeProperties(this.nodeId);
			testSql = prop.getProperty("proxool.house-keeping-test-sql");
			rs = st.executeQuery(testSql);
			if (rs.next())
				count = 1;
		} catch (Exception localException8) {
			boolean i;
			if (con == null) {
				try {
					try {
						Thread.currentThread();
						Thread.sleep(30000L);
					} catch (InterruptedException ie1) {
						ie1.printStackTrace();
					}
					con = DriverManager.getConnection("proxool." + this.nodeId);
					st = con.createStatement();
					Properties prop = this.cluster.getNodeProperties(this.nodeId);
					testSql = prop.getProperty("proxool.house-keeping-test-sql");
					rs = st.executeQuery(testSql);
					if (rs.next()) {
						i = true;
						try {
							if (rs != null)
								rs.close();
							if (st != null)
								st.close();
							if (con != null)
								con.close();
						} catch (Exception localException2) {
						}
						return i;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					i = false;
					try {
						if (rs != null)
							rs.close();
						if (st != null)
							st.close();
						if (con != null)
							con.close();
					} catch (Exception localException3) {
					}
					return i;
				}
			}
			if (st == null) {
				try {
					try {
						Thread.currentThread();
						Thread.sleep(20000L);
					} catch (InterruptedException ie1) {
						ie1.printStackTrace();
					}
					st = con.createStatement();
					Properties prop = this.cluster.getNodeProperties(this.nodeId);
					testSql = prop.getProperty("proxool.house-keeping-test-sql");
					rs = st.executeQuery(testSql);
					if (rs.next()) {
						i = true;
						try {
							if (rs != null)
								rs.close();
							if (st != null)
								st.close();
							if (con != null)
								con.close();
						} catch (Exception localException4) {
						}
						return i;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					i = false;
					try {
						if (rs != null)
							rs.close();
						if (st != null)
							st.close();
						if (con != null)
							con.close();
					} catch (Exception localException5) {
					}
					return i;
				}
			}
			if (rs == null)
				try {
					try {
						Thread.currentThread();
						Thread.sleep(10000L);
					} catch (InterruptedException ie1) {
						ie1.printStackTrace();
					}
					Properties prop = this.cluster.getNodeProperties(this.nodeId);
					testSql = prop.getProperty("proxool.house-keeping-test-sql");
					rs = st.executeQuery(testSql);
					if (rs.next()) {
						i = true;
						try {
							if (rs != null)
								rs.close();
							if (st != null)
								st.close();
							if (con != null)
								con.close();
						} catch (Exception localException6) {
						}
						return i;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					i = false;
					try {
						if (rs != null)
							rs.close();
						if (st != null)
							st.close();
						if (con != null)
							con.close();
					} catch (Exception localException7) {
					}
					return i;
				}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (Exception localException9) {
			}
		}
		return count == 1;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public void handleFault() {
		try {
			this.cluster.removeNode(this.nodeId);
			alarmNotice.all();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resumeFault() {
		try {
			this.cluster.addNode(this.nodeId);
			alarmNotice.all();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return this.nodeId;
	}
}