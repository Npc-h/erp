package cn.itcast.erp.biz;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 报表业务接口
 * @author Administrator
 *
 */
public interface IReportBiz {
	
	/**
	 * 销售统计报表
	 * @return
	 */
	List ordersReport(Date startDate, Date endDate);
	
	/**
	 * 销售趋势图
	 * @param year
	 * @return
	 */
	List<Map<String,Object>> getSumMoney(int year);
}
