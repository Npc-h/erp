package cn.itcast.erp.job;

import cn.itcast.erp.biz.IStoredetailBiz;

/**
 * 自动发预警邮件
 * @author Administrator
 *
 */
public class MailJob {
	
	private IStoredetailBiz storedetailBiz;

	public void setStoredetailBiz(IStoredetailBiz storedetailBiz) {
		this.storedetailBiz = storedetailBiz;
	}

	/**
	 * 发送预警邮件调用 的方法
	 */
	public void sendStorealertMail(){
		try {
			storedetailBiz.sendStorealertMail();
			System.out.println("发送预警邮件!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
