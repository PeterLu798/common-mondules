package com.yi23.rdbms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yi23.rdbms.node.AlarmNotice;

public class DefaultAlarmNotice implements AlarmNotice {
	private static final Logger logger = LoggerFactory.getLogger(DefaultAlarmNotice.class);

	public void email() {
	}

	public void sms() {
	}

	public void all() {
		logger.debug("Call Send Email or Sms!");
		email();
		sms();
	}

	public static AlarmNotice getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final AlarmNotice INSTANCE = new DefaultAlarmNotice();
	}
}