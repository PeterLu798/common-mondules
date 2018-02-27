package com.yi23.rdbms.node;

import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.admin.SnapshotIF;

import com.yi23.rdbms.RDBMS;

public class ClusterNodeReadWrite implements ClusterNode {
	private String nodeId;

	public ClusterNodeReadWrite(String nodeId, RDBMS cluster) {
		this.nodeId = nodeId;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public boolean checkStatus() {
		try {
			SnapshotIF snapShot = ProxoolFacade.getSnapshot(this.nodeId);
			if (snapShot.getMaximumConnectionCount() == snapShot.getActiveConnectionCount()) {
				Thread.currentThread();
				Thread.sleep(15000L);
				snapShot = ProxoolFacade.getSnapshot(this.nodeId);
				if (snapShot.getMaximumConnectionCount() == snapShot.getActiveConnectionCount()) {
					System.out.println("max conn=" + snapShot.getMaximumConnectionCount() + ",active conn="
							+ snapShot.getActiveConnectionCount()
							+ ",System will try to kill some not active connection......");
					ProxoolFacade.killAllConnections(this.nodeId, "the number of connection has reach max number.",
							true);
					System.out.println("after killed,the max conn=" + snapShot.getMaximumConnectionCount()
							+ ",active conn=" + snapShot.getActiveConnectionCount() + "......");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void handleFault() {
	}

	public void resumeFault() {
	}
}
