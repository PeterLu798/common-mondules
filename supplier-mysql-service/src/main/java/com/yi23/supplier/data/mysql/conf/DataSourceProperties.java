package com.yi23.supplier.data.mysql.conf;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.yi23.rdbms.config.Cluster;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>Title: DataSourceProperties</p>
 * <p>Description:  </p>
 * <p>Company: www.yi23.net </p> 
 * @author lubaijiang
 * @date 2018年2月23日 下午7:37:43
 *
 */
@Getter
@Setter
@ConfigurationProperties(prefix = DataSourceProperties.DATASOURCE_PREFIX)
public class DataSourceProperties {
	
	public static final String DATASOURCE_PREFIX = "yi23.supplier.dataSource";
	
	private List<Cluster> clusters;
	
}
