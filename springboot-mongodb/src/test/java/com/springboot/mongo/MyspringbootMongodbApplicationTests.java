package com.springboot.mongo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.mongo.dto.SupplierProductDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyspringbootMongodbApplicationTests {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void contextLoads() {
		SupplierProductDto dto = new SupplierProductDto();
		dto.setId("4");
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("sku", "eeeeppppp-111111-55555555555");
//		dto.setProduct(map);
		dto.setCreateTime(new Date());
		mongoTemplate.insert(dto);
//		mongoTemplate.find(query, entityClass)
	}

}
