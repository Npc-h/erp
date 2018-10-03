package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IStoreBiz;
import cn.itcast.erp.dao.IStoreDao;
import cn.itcast.erp.entity.Store;
/**
 * 仓库业务逻辑类
 * @author Administrator
 *
 */
public class StoreBiz extends BaseBiz<Store> implements IStoreBiz {

	private IStoreDao storeDao;
	
	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
		super.setBaseDao(this.storeDao);
	}
	
}
