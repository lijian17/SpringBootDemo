package net.dxs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.PageHelper;

import net.dxs.mapper.SysUserMapper;
import net.dxs.mapper.SysUserMapperCustom;
import net.dxs.pojo.SysUser;
import net.dxs.service.UserService;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserMapper userMapper;

	@Autowired
	private SysUserMapperCustom userMapperCustom;

	@Override
	public void saveUser(SysUser user) throws Exception {
		userMapper.insert(user);
	}

	@Override
	public void updateUser(SysUser user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void deleteUser(String userId) {
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public SysUser queryUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<SysUser> queryUserList(SysUser user) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(user.getUsername())) {
			// criteria.andEqualTo("username", user.getUsername());
			criteria.andLike("username", "%" + user.getUsername() + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%" + user.getNickname() + "%");
		}
		List<SysUser> userList = userMapper.selectByExample(example);
		return userList;
	}

	@Override
	public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
		// 开始分页
		PageHelper.startPage(page, pageSize);

		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%" + user.getNickname() + "%");
		}
		example.orderBy("registTime").desc();
		List<SysUser> userList = userMapper.selectByExample(example);

		return userList;
	}

	@Override
	public SysUser queryUserByIdCustom(String userId) {
		List<SysUser> userList = userMapperCustom.queryUserSimplyInfoById(userId);
		if (userList != null && !userList.isEmpty()) {
			return (SysUser) userList.get(0);
		}
		return null;
	}

	@Override
	public void saveUserTransactional(SysUser user) {
		userMapper.insert(user);
		int a = 1 / 0;
		user.setIsDelete(1);
		userMapper.updateByPrimaryKeySelective(user);
	}
}
