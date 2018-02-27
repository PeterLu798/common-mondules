package com.yi23.supplier.data.mysql.conf;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yi23.rdbms.RDBMSDataSource;
import com.yi23.rdbms.config.RDBMSConfigHelper;
/**
 * <p>Title: DataSourceConf</p>
 * <p>Description: 配置数据源 </p>
 * <p>Company: www.yi23.net </p> 
 * @author lubaijiang
 * @date 2018年2月23日 下午8:00:33
 *
 */
@Configuration
@MapperScan(annotationClass = Mapper.class ,basePackages = {"com.yi23.supplier.data.mysql.*.*.mapper"})
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConf {

	private static final String DATASOURCEID = "supplier-data-mysql-service";

	@Autowired
	private DataSourceProperties properties;

	/**
	 * 数据源配置
	 * 
	 * @return DataSource 数据源
	 */
	@Bean
	public RDBMSDataSource datasource() {
		RDBMSDataSource datasource = new RDBMSDataSource(RDBMSConfigHelper.help(properties.getClusters()));
		datasource.setClusterId(DataSourceConf.DATASOURCEID);
		return datasource;
	}

}
