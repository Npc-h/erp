package cn.itcast.erp.dao;

import java.util.List;

import cn.itcast.erp.entity.Storealert;
import cn.itcast.erp.entity.Storedetail;
/**
 * 仓库库存数据访问接口
 * @author Administrator
 *
 */
public interface IStoredetailDao extends IBaseDao<Storedetail>{

	/**
	 * 获取库存预警列表
	 * @return
	 */
	List<Storealert> getStorealertList();
}
