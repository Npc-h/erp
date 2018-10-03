package cn.itcast.erp.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 报表数据访问接口
 * @author Administrator
 *
 */
public interface IReportDao {

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
