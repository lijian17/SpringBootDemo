package net.dxs.mapper;

import java.util.List;

import net.dxs.pojo.SysUser;

public interface SysUserMapperCustom {

	List<SysUser> queryUserSimplyInfoById(String id);
}
