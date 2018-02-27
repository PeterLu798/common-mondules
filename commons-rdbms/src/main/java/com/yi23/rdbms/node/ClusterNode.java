package com.yi23.rdbms.node;

import com.yi23.rdbms.DefaultAlarmNotice;

public abstract interface ClusterNode {
	public static final AlarmNotice alarmNotice = DefaultAlarmNotice.getInstance();

	public abstract String getNodeId();

	public abstract boolean checkStatus();

	public abstract void handleFault();

	public abstract void resumeFault();
}
