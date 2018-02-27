package com.yi23.rdbms.node;

import java.util.ArrayList;
import java.util.List;

public class ClusterNodeMonitor implements Runnable {
	private long interval = 60000L;
	private Thread thread;
	private boolean alive = true;
	private ArrayList<ClusterNode> clusterNodes = new ArrayList(5);
	private ArrayList<ClusterNode> badNodes = new ArrayList();

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public void start(boolean daemon) {
		this.thread = new Thread(this);
		this.thread.setDaemon(daemon);
		this.thread.start();
	}

	public void addClusterNode(ClusterNode clusterNode) {
		if (clusterNode == null)
			return;
		this.clusterNodes.add(clusterNode);
	}

	public ArrayList<ClusterNode> getActiveNodes() {
		return this.clusterNodes;
	}

	public void run() {
		if (this.interval <= 0L)
			throw new IllegalArgumentException("interval must be >0");
		while (this.alive) {
			sleep();
			forceDetectingBadNode();
			forceDetectingNode();
		}
	}

	public void setStop() {
		this.alive = false;
		this.thread = null;
	}

	private void sleep() {
		try {
			Thread.sleep(this.interval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void forceDetectingBadNode() {
		if (this.badNodes.size() > 0) {
			List<ClusterNode> resumeNodes = new ArrayList<ClusterNode>(this.badNodes.size());
			for (int i = 0; i < this.badNodes.size(); i++) {
				ClusterNode node = (ClusterNode) this.badNodes.get(i);
				boolean result = node.checkStatus();
				try {
					if (result)
						resumeNodes.add(node);
				} catch (Exception localException) {
				}
			}
			if (resumeNodes.size() > 0) {
				for (ClusterNode rNode : resumeNodes) {
					this.badNodes.remove(rNode);
				}
				synchronized (this.clusterNodes) {
					this.clusterNodes.addAll(resumeNodes);
				}
				handleResumeNodeChanged(resumeNodes);
			}
		}
	}

	private void forceDetectingNode() {
		int threadCount = this.clusterNodes.size();
		if (threadCount == 0)
			return;
		ArrayList activeNodes = new ArrayList();
		ArrayList faultNodes = new ArrayList();
		for (int i = 0; i < threadCount; i++) {
			ClusterNode node = (ClusterNode) this.clusterNodes.get(i);
			boolean result = node.checkStatus();
			try {
				if (result)
					activeNodes.add(node);
				else
					faultNodes.add(node);
			} catch (Exception ex) {
				faultNodes.add(node);
			}
		}
		if (faultNodes.size() > 0)
			synchronized (this.clusterNodes) {
				this.clusterNodes.clear();
				this.clusterNodes.addAll(activeNodes);
				this.badNodes.addAll(faultNodes);
				System.out.println("Cluster Node List" + this.clusterNodes);
				System.out.println("bad Node List" + this.badNodes);
				handleFaultNodeChanged(faultNodes);
			}
	}

	private void handleFaultNodeChanged(List<ClusterNode> faultNodes) {
		for (ClusterNode node : faultNodes)
			node.handleFault();
	}

	private void handleResumeNodeChanged(List<ClusterNode> resumeNodes) {
		for (ClusterNode node : resumeNodes)
			node.resumeFault();
	}
}
