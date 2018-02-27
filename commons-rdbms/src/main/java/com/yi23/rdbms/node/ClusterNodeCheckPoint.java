package com.yi23.rdbms.node;

import java.util.concurrent.Callable;

public class ClusterNodeCheckPoint implements Callable<Boolean> {
	private ClusterNode node;

	public ClusterNodeCheckPoint(ClusterNode node) {
		this.node = node;
	}

	public Boolean call() throws Exception {
		return Boolean.valueOf(this.node.checkStatus());
	}
}
