package cn.itcast.erp.biz.impl;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.erp.biz.IRoleBiz;
import cn.itcast.erp.dao.IMenuDao;
import cn.itcast.erp.dao.IRoleDao;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Menu;
import cn.itcast.erp.entity.Role;
import cn.itcast.erp.entity.Tree;
import redis.clients.jedis.Jedis;
/**
 * 角色业务逻辑类
 * @author Administrator
 *
 */
public class RoleBiz extends BaseBiz<Role> implements IRoleBiz {

	private IRoleDao roleDao;
	private IMenuDao menuDao;
	private Jedis jedis;
	
	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
		super.setBaseDao(this.roleDao);
	}
	
	/**
	 * 获取角色菜单权限
	 * @param uuid 角色编号
	 */
	public List<Tree> readRoleMenus(Long uuid){
		List<Tree> treeList = new ArrayList<Tree>();
		//获取角色信息
		Role role = roleDao.get(uuid);
		//获取角色菜单
		List<Menu> roleMenus = role.getMenus();
		//根菜单
		Menu root = menuDao.get("0");
		Tree t1 = null;
		Tree t2 = null;
		//一级菜单
		for(Menu m : root.getMenus()){
			t1 = new Tree();
			t1.setId(m.getMenuid());
			t1.setText(m.getMenuname());
			//二级菜单
			for(Menu m2 : m.getMenus()){
				t2 = new Tree();
				t2.setId(m2.getMenuid());
				t2.setText(m2.getMenuname());
				//如果角色下包含有这个权限菜单，让它勾选上
				if(roleMenus.contains(m2)){
					t2.setChecked(true);
				}
				t1.getChildren().add(t2);
			}
			treeList.add(t1);
		}
		return treeList;
	}
	
	/**
	 * 更新角色权限
	 * @param uuid 角色编号
	 * @param checkedStr 勾选中菜单的ID字符串，以逗号分割
	 */
	public void updateRoleMenus(Long uuid, String checkedStr){
		//得到角色信息，进入持久化状态
		Role role = roleDao.get(uuid);
		//清除角色下的菜单权限, delete from role_menu where roleuuid=?
		role.setMenus(new ArrayList<Menu>());
		
		String[] ids = checkedStr.split(",");
		Menu menu = null;
		for(String id : ids){
			menu = menuDao.get(id);
			//保存角色下的菜单权限 insert into role_menu;
			role.getMenus().add(menu);
		}
		
		//1. 通过角色，反查询属于这个角色有哪些用户
		List<Emp> empList = role.getEmps();
		//2. 清除这些用户的菜单权限缓存
		try {
			for(Emp emp : empList){
				//清除每个用户的菜单权限缓存
				jedis.del("menuList_" + emp.getUuid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
