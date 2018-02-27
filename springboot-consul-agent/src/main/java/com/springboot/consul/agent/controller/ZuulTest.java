package com.springboot.consul.agent.controller;

import org.springframework.stereotype.Service;

@Service("zuulTest")
public class ZuulTest {

	public String zuul(){
		return "------------hello zuul----------";
	}
}
