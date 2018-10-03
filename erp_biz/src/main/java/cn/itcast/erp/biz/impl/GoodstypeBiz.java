package cn.itcast.erp.biz.impl;
import cn.itcast.erp.biz.IGoodstypeBiz;
import cn.itcast.erp.dao.IGoodstypeDao;
import cn.itcast.erp.entity.Goodstype;
/**
 * 商品分类业务逻辑类
 * @author Administrator
 *
 */
public class GoodstypeBiz extends BaseBiz<Goodstype> implements IGoodstypeBiz {

	private IGoodstypeDao goodstypeDao;
	
	public void setGoodstypeDao(IGoodstypeDao goodstypeDao) {
		this.goodstypeDao = goodstypeDao;
		super.setBaseDao(this.goodstypeDao);
	}
	
}
