package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
/**
 * 部门业务逻辑类
 * @author Administrator
 *
 */
public class DepBiz extends BaseBiz<Dep> implements IDepBiz {

	private IDepDao depDao;
	
	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
		super.setBaseDao(this.depDao);
	}
	
}
