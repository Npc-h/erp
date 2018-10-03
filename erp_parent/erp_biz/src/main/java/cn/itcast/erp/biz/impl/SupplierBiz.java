package cn.itcast.erp.biz.impl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.itcast.erp.biz.ISupplierBiz;
import cn.itcast.erp.dao.ISupplierDao;
import cn.itcast.erp.entity.Supplier;
import cn.itcast.erp.exception.ErpException;
/**
 * 供应商业务逻辑类
 * @author Administrator
 *
 */
public class SupplierBiz extends BaseBiz<Supplier> implements ISupplierBiz {

	private ISupplierDao supplierDao;
	
	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
		super.setBaseDao(this.supplierDao);
	}

	/**
	 * 导出数据
	 */
	public void export(OutputStream os, Supplier t1) {
		//获取要导出的数据列表
		List<Supplier> list = supplierDao.getList(t1, null, null);
		//创建一个工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		
		String sheetName = "";
		if(Supplier.TYPE_CUSTOMER.equals(t1.getType())){
			sheetName = "客户";
		}
		if(Supplier.TYPE_SUPPLIER.equals(t1.getType())){
			sheetName = "供应商";
		}
		//创建一个工作表
		HSSFSheet sheet = wb.createSheet(sheetName);
		//创建一行,行的索引是从0开始, 写标题
		HSSFRow row = sheet.createRow(0);
		String[] header = {"名称","地址","联系人","电话","Email"};
		int[] width = {5000,8000,4000,8000,10000};
		HSSFCell cell = null;
		for(int i = 0; i < header.length; i++){
			cell = row.createCell(i);
			cell.setCellValue(header[i]);
			//设置列宽
			sheet.setColumnWidth(i, width[i]);
		}
		//导出的内容
		int rowCount = 1;
		for(Supplier supplier : list){
			row = sheet.createRow(rowCount);
			row.createCell(0).setCellValue(supplier.getName());//名称
			row.createCell(1).setCellValue(supplier.getAddress());//地址
			row.createCell(2).setCellValue(supplier.getContact());//联系人
			row.createCell(3).setCellValue(supplier.getTele());//电话
			row.createCell(4).setCellValue(supplier.getEmail());//Email
			rowCount++;
		}
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void doImport(InputStream is) throws IOException {
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
			String type = "";
			if("供应商".equals(sheet.getSheetName())){
				type = Supplier.TYPE_SUPPLIER;
			}else if("客户".equals(sheet.getSheetName())){
				type = Supplier.TYPE_CUSTOMER;
			}else{
				throw new ErpException("工作表名称不正确");
			}
			
			//读取数据
			//最后一行的行号
			int lastRow = sheet.getLastRowNum();
			Supplier supplier = null;
			for(int i = 1; i <= lastRow; i++){
				supplier = new Supplier();
				supplier.setName(sheet.getRow(i).getCell(0).getStringCellValue());//供应商名称
				//判断是否已经存在，通过名称来判断
				List<Supplier> list = supplierDao.getList(null, supplier, null);
				if(list.size() > 0){
					supplier = list.get(0);
				}
				supplier.setAddress(sheet.getRow(i).getCell(1).getStringCellValue());//地址
				supplier.setContact(sheet.getRow(i).getCell(2).getStringCellValue());//联系人
				supplier.setTele(sheet.getRow(i).getCell(3).getStringCellValue());//电话
				supplier.setEmail(sheet.getRow(i).getCell(4).getStringCellValue());//Email
				if(list.size() == 0){
					//新增
					supplier.setType(type);
					supplierDao.add(supplier);
				}
			}			
		} finally{
			if(null != wb){
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
