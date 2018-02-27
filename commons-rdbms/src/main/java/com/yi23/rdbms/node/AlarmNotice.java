package com.yi23.rdbms.node;

public abstract interface AlarmNotice {
	public abstract void sms();

	public abstract void email();

	public abstract void all();
}
