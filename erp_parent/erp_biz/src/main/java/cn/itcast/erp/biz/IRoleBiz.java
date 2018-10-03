package cn.itcast.erp.biz;
import java.util.List;

import cn.itcast.erp.entity.Role;
import cn.itcast.erp.entity.Tree;
/**
 * 角色业务逻辑层接口
 * @author Administrator
 *
 */
public interface IRoleBiz extends IBaseBiz<Role>{

	/**
	 * 获取角色菜单权限
	 * @param uuid 角色编号
	 */
	List<Tree> readRoleMenus(Long uuid);
	
	/**
	 * 更新角色权限
	 * @param uuid 角色编号
	 * @param checkedStr 勾选中菜单的ID字符串，以逗号分割
	 */
	void updateRoleMenus(Long uuid, String checkedStr);
}

