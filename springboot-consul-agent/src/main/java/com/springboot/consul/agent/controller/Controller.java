package com.springboot.consul.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.consul.agent.rest.MysqlServiceGateWay;

@RestController
public class Controller {
	
	@Autowired
	MysqlServiceGateWay gateWay;

	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String test(){
		try {
			System.out.println("开始测试...");
			gateWay.test();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
}
