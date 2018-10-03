package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IReturnorderdetailBiz;
import cn.itcast.erp.dao.IReturnorderdetailDao;
import cn.itcast.erp.entity.Returnorderdetail;
/**
 * 退货订单明细业务逻辑类
 * @author Administrator
 *
 */
public class ReturnorderdetailBiz extends BaseBiz<Returnorderdetail> implements IReturnorderdetailBiz {

	private IReturnorderdetailDao returnorderdetailDao;
	
	public void setReturnorderdetailDao(IReturnorderdetailDao returnorderdetailDao) {
		this.returnorderdetailDao = returnorderdetailDao;
		super.setBaseDao(this.returnorderdetailDao);
	}
	
}
