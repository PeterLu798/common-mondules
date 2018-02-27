package com.springboot.mongo.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>Title: SupplierProductDto</p>
 * <p>Description: </p>
 * <p>Company: </p> 
 * @author lubaijiang
 * @date 2017年9月2日 上午11:57:27
 *
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SupplierProductDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2162871494223587295L;
	/**
	 * id
	 */
	@Id
	private String id;
	/**
	 * 产品信息
	 */
	private Map<String,Object> product; 
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
