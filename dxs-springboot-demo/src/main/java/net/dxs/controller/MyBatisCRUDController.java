package net.dxs.controller;

import java.util.Date;
import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.dxs.pojo.DxsJSONResult;
import net.dxs.pojo.SysUser;
import net.dxs.service.UserService;

@RestController
@RequestMapping("mybatis")
public class MyBatisCRUDController {

	@Autowired
	private UserService userService;

	@Autowired
	private Sid sid;

	@RequestMapping("/saveUser")
	public DxsJSONResult saveUser() throws Exception {

		String userId = sid.nextShort();

		SysUser user = new SysUser();
		user.setId(userId);
		user.setUsername("dxs" + new Date());
		user.setNickname("dxs" + new Date());
		user.setPassword("abc123");
		user.setIsDelete(0);
		user.setRegistTime(new Date());

		userService.saveUser(user);

		return DxsJSONResult.ok("保存成功");
	}

	@RequestMapping("/updateUser")
	public DxsJSONResult updateUser() {

		SysUser user = new SysUser();
		user.setId("10011001");
		user.setUsername("10011001-updated" + new Date());
		user.setNickname("10011001-updated" + new Date());
		user.setPassword("10011001-updated");
		user.setIsDelete(0);
		user.setRegistTime(new Date());

		userService.updateUser(user);

		return DxsJSONResult.ok("保存成功");
	}

	@RequestMapping("/deleteUser")
	public DxsJSONResult deleteUser(String userId) {
		userService.deleteUser(userId);
		return DxsJSONResult.ok("删除成功");
	}

	@RequestMapping("/queryUserById")
	public DxsJSONResult queryUserById(String userId) {
		return DxsJSONResult.ok(userService.queryUserById(userId));
	}

	@RequestMapping("/queryUserList")
	public DxsJSONResult queryUserList() {
		SysUser user = new SysUser();
		user.setUsername("dxs");
		List<SysUser> userList = userService.queryUserList(user);
		return DxsJSONResult.ok(userList);
	}

	@RequestMapping("/queryUserListPaged")
	public DxsJSONResult queryUserListPaged(Integer page) {
		if (page == null) {
			page = 1;
		}
		int pageSize = 2;
		SysUser user = new SysUser();
		// user.setNickname("lijian");
		List<SysUser> userList = userService.queryUserListPaged(user, page, pageSize);
		return DxsJSONResult.ok(userList);
	}

	@RequestMapping("/queryUserByIdCustom")
	public DxsJSONResult queryUserByIdCustom(String userId) {
		return DxsJSONResult.ok(userService.queryUserByIdCustom(userId));
	}

	@RequestMapping("/saveUserTransactional")
	public DxsJSONResult saveUserTransactional() {

		String userId = sid.nextShort();

		SysUser user = new SysUser();
		user.setId(userId);
		user.setUsername("lijian" + new Date());
		user.setNickname("lijian" + new Date());
		user.setPassword("abc123");
		user.setIsDelete(0);
		user.setRegistTime(new Date());

		userService.saveUserTransactional(user);

		return DxsJSONResult.ok("保存成功");
	}
}
