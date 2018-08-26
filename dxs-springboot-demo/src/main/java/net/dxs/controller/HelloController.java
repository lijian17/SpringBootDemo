package net.dxs.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.dxs.pojo.DxsJSONResult;
import net.dxs.pojo.Resource;

@RestController
public class HelloController {
	
	@Autowired
	private Resource resource;
	
	@RequestMapping("/hello")
	public Object hello() {
		return "hello springboot";
	}
	
	@RequestMapping("/getResource")
	public DxsJSONResult getResource() {
		Resource bean = new Resource();
		BeanUtils.copyProperties(resource, bean);
		return DxsJSONResult.ok(bean);
	}

}
