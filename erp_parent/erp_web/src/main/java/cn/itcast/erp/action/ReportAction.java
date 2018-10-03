package cn.itcast.erp.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IReportBiz;

/**
 * 报表Action
 * @author Administrator
 *
 */
public class ReportAction {
	
	private IReportBiz reportBiz;
	
	private Date startDate;
	private Date endDate;
	
	private int year;

	/**
	 * 销售统计报表
	 */
	public void orderReport(){
		List list = reportBiz.ordersReport(startDate,endDate);
		write(JSON.toJSONString(list));
	}
	
	public void trendReport(){
		List<Map<String,Object>> list = reportBiz.getSumMoney(year);
		write(JSON.toJSONString(list));
	}

	public void setReportBiz(IReportBiz reportBiz) {
		this.reportBiz = reportBiz;
	}
	
	/**
	 * 输出字符串到前端
	 * @param jsonString
	 */
	public void write(String jsonString){
		try {
			//响应对象
			HttpServletResponse response = ServletActionContext.getResponse();
			//设置编码
			response.setContentType("text/html;charset=utf-8"); 
			//输出给页面
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
