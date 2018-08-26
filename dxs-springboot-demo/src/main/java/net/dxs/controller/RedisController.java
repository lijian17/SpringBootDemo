package net.dxs.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.dxs.pojo.DxsJSONResult;
import net.dxs.pojo.SysUser;
import net.dxs.utils.JsonUtils;

@RestController
@RequestMapping("redis")
public class RedisController {

	@Autowired
	private StringRedisTemplate strRedis;

	@RequestMapping("/test")
	public DxsJSONResult test() {
		strRedis.opsForValue().set("dxs-cache", "hello 54大学生~~~~~~");
		SysUser user = new SysUser();
		user.setId("100111");
		user.setUsername("dxs");
		user.setPassword("abc123");
		user.setIsDelete(0);
		user.setRegistTime(new Date());
		strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(user));
		SysUser jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), SysUser.class);
		return DxsJSONResult.ok(jsonUser);
	}
}
