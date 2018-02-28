package com.yi23.rdbms.config;

import java.util.List;

public class RDBMSConfigHelper {
	private List<Cluster> clusters;
	private static volatile RDBMSConfigHelper rdbmsConfig = null;

	private RDBMSConfigHelper() {
	}

	private RDBMSConfigHelper(List<Cluster> clusters) {
		this.clusters = clusters;
	}

	public static  RDBMSConfigHelper help(List<Cluster> clusters) {
		if (rdbmsConfig == null) {
			synchronized (RDBMSConfigHelper.class) {
				if (rdbmsConfig == null) {
					rdbmsConfig = new RDBMSConfigHelper(clusters);
				}
			}
		}
		return rdbmsConfig;
	}

	public List<Cluster> getClusters() {
		return this.clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}
}
