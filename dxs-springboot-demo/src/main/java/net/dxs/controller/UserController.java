package net.dxs.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.dxs.pojo.DxsJSONResult;
import net.dxs.pojo.User;

//@Controller
@RestController // @RestController = @Controller + @ResponseBody
@RequestMapping("user")
public class UserController {

	@RequestMapping("/getUser")
//	@ResponseBody
	public User getUser() {
		User u = new User();
		u.setName("lijian");
		u.setPassword("123456");
		u.setAge(18);
		u.setBirthday(new Date());
		u.setDesc(null);
		return u;
	}

	@RequestMapping("/getUserJson")
//	@ResponseBody
	public DxsJSONResult getUserJson() {
		User u = new User();
		u.setName("lijian");
		u.setPassword("123456");
		u.setAge(18);
		u.setBirthday(new Date());
		u.setDesc(null);

		return DxsJSONResult.ok(u);
	}

}
