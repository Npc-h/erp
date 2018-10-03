package cn.itcast.erp.action;
import cn.itcast.erp.biz.IStoreoperBiz;
import cn.itcast.erp.entity.Storeoper;

/**
 * 仓库操作记录Action 
 * @author Administrator
 *
 */
public class StoreoperAction extends BaseAction<Storeoper> {

	private IStoreoperBiz storeoperBiz;

	public void setStoreoperBiz(IStoreoperBiz storeoperBiz) {
		this.storeoperBiz = storeoperBiz;
		super.setBaseBiz(this.storeoperBiz);
	}

}
