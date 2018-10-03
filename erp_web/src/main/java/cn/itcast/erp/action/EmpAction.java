package cn.itcast.erp.action;
import java.util.List;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.entity.Emp;
import cn.itcast.erp.entity.Menu;
import cn.itcast.erp.entity.Tree;

/**
 * 员工Action 
 * @author Administrator
 *
 */
public class EmpAction extends BaseAction<Emp> {

	private IEmpBiz empBiz;

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
		super.setBaseBiz(this.empBiz);
	}
	
	private String oldPwd;//旧密码
	private String newPwd;//新密码

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	/**
	 * 修改密码调用的方法
	 */
	public void updatePwd(){
		Emp loginUser = getLoginUser();
		//session是否会超时，用户是否登陆过了
		if(null == loginUser){
			ajaxReturn(false, "亲，您还没有登陆");
			return;
		}
		try {
			empBiz.updatePwd(loginUser.getUuid(), oldPwd, newPwd);
			ajaxReturn(true, "修改密码成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "修改密码失败");
		}
	}
	
	/**
	 * 重置密码调用的方法
	 */
	public void updatePwd_reset(){
		
		try {
			empBiz.updatePwd_reset(getId(), newPwd);
			ajaxReturn(true, "重置密码成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "重置密码失败");
		}
	}
	
	private String checkedStr;//勾选中角色的ID字符串，以逗号分割
	public String getCheckedStr() {
		return checkedStr;
	}

	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}

	/**
	 * 获取用户角色
	 */
	public void readEmpRoles(){
		List<Tree> roleList = empBiz.readEmpRoles(getId());
		write(JSON.toJSONString(roleList));
	}
	
	public void updateEmpRoles(){
		try {
			empBiz.updateEmpRoles(getId(), checkedStr);
			ajaxReturn(true, "更新用户角色成功");
		} catch (Exception e) {
			ajaxReturn(false, "更新用户角色失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取用户的菜单权限
	 */
	public void getMenusByEmpuuid(){
		if(null != getLoginUser()){
			List<Menu> menuList = empBiz.getMenusByEmpuuid(getLoginUser().getUuid());
			write(JSON.toJSONString(menuList));
		}
	}
}
