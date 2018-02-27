package com.yi23.rdbms.dynamic;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	public static DynamicDataSource instance(DataSource master,
			Map<Object, Object> clusters) {
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(clusters);
		dataSource.setDefaultTargetDataSource(master);
		return dataSource;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceHolder.getDataSource();
	}
	
	
}
