package com.information.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.StaticBucketMap;
import org.apache.commons.collections4.Put;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import com.information.entity.AwardEntity;
import com.information.entity.Tips;
import com.information.service.AwardService;
import com.information.service.PageService;
import com.information.service.UserinfoService;
import com.information.serviceimp.AwardServiceImp;
import com.information.serviceimp.PageImpService;
import com.information.serviceimp.UserinfoServiceImp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class OutPutAwardServlet extends HttpServlet {	
	/**
	 * 
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.println("已经实例化了DeleteUserServlet");
	}
	
	/**
	 * 获取 接受 json
	 */
	public static String getRequestJsonString(HttpServletRequest request)
            throws IOException {
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
            return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");
        // POST
        } else {
            return getRequestPostStr(request);
        }
    }

    /**      
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException      
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**      
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException      
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Tips tips;
		JSONObject jsonTips;
		JSONObject jsonObject;
		List<AwardEntity> award = null;
		
		// 将 json 变成字符串
		String params = getRequestJsonString(req);
		System.out.println(params);
		// 将 json 字符串中的数据提取出来
		jsonObject = JSONObject.fromObject(params);
	  	int departmentId = (int)jsonObject.get("departmentId");
	  	String identity = (String)jsonObject.get("identity");
	  	String type = (String)jsonObject.get("type");
	  	String rank = (String)jsonObject.get("rank");
	  	String clock1 = (String)jsonObject.get("clock1");
	  	String clock2 = (String)jsonObject.get("clock2");
	  	String uName = (String)jsonObject.get("uName");
	  	int userinfoId = (int)jsonObject.get("userinfoId");

	  	AwardService lg =new AwardServiceImp();
	  	award = lg.awardinfo(new Object[] {departmentId, identity, type, rank, clock1, clock2, uName, userinfoId});
		
	  //第一步创建workbook  
        HSSFWorkbook wb = new HSSFWorkbook();  
          
        //第二步创建sheet  
        HSSFSheet sheet = wb.createSheet("sheet1");  
        
        //第三步创建行row:添加表头0行  
        
        //合并单元格 参数说明：1：开始行 2：结束行  3：开始列 4：结束列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));
        HSSFRow Hrow = sheet.createRow(0); 
        Hrow.setHeightInPoints(50);
        HSSFCellStyle  Hstyle = wb.createCellStyle();  
        Hstyle.setAlignment(HorizontalAlignment.CENTER);
        Hstyle.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont Hfont=wb.createFont();
        Hfont.setFontName("黑体");  
        Hfont.setFontHeightInPoints((short) 20);//设置字体大小   
        Hstyle.setFont(Hfont);//选择创建的字体格式 
        HSSFCell Hcell = Hrow.createCell(0); //第一个单元格  
        Hcell.setCellValue("技能竞赛获奖情况统计表");  
        Hcell.setCellStyle(Hstyle); 
        
        HSSFRow row = sheet.createRow(1);  
      //heightInPoints 设置的值永远是height属性值的20倍
        row.setHeightInPoints(60);
        HSSFCellStyle  style = wb.createCellStyle();      
        HSSFCellStyle  style1 = wb.createCellStyle();      
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        
        //生成一个字体
        HSSFFont font=wb.createFont();
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);         //字体增粗
        font.setFontName("黑体");  
        font.setFontHeightInPoints((short) 14);//设置字体大小   
        style.setFont(font);//选择创建的字体格式 

        //第四步创建单元格  
        HSSFCell cell = row.createCell(0); //第一个单元格  
        cell.setCellValue("序号");  
        cell.setCellStyle(style);  
        sheet.setColumnWidth(0, 2000);

        cell = row.createCell(1); 
        cell.setCellValue("姓名");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(1, 3000);
        
        cell = row.createCell(2);         
        cell.setCellValue("学号");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(2, 3000);
        
        cell = row.createCell(3);         
        cell.setCellValue("班级");  
        cell.setCellStyle(style);  
        sheet.setColumnWidth(3, 3000);
        
        cell = row.createCell(4);         
        cell.setCellValue("系部");  
        cell.setCellStyle(style);  
        sheet.setColumnWidth(4, 4000);
        
        cell = row.createCell(5);         
        cell.setCellValue("级别");  
        cell.setCellStyle(style);  
        sheet.setColumnWidth(5, 3000);
        
        cell = row.createCell(6);         
        cell.setCellValue("获奖项目");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(6, 10000);
        
        cell = row.createCell(7);         
        cell.setCellValue("等级");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(7, 3000);
        
        cell = row.createCell(8);         
        cell.setCellValue("竞赛种类");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(8, 3000);
        
        cell = row.createCell(9);         
        cell.setCellValue("发证时间");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(9, 4000);
        
        cell = row.createCell(10);         
        cell.setCellValue("发证单位");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(10, 6000);
        
        cell = row.createCell(11);         
        cell.setCellValue("每年学期");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(11, 4000);
          
        cell = row.createCell(12);         
        cell.setCellValue("备注");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(12, 6000);
       
        //第五步插入数据  
        Iterator<AwardEntity> iter = award.iterator();
        int i = 0;
        while(iter.hasNext()) {
        	AwardEntity ae = iter.next();
        	
        	row = sheet.createRow(i+2);
        	
        	HSSFCell cell1 = row.createCell(0); 
        	cell1.setCellValue(i+1); 
        	cell1.setCellStyle(style1);

        	cell1 = row.createCell(1); 
        	cell1.setCellValue(ae.getuName());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(2); 
        	cell1.setCellValue(ae.getStuId());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(3); 
        	cell1.setCellValue(ae.getStuClass());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(4); 
        	cell1.setCellValue(ae.getDepartment());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(5); 
        	cell1.setCellValue(ae.getRank());
        	cell1.setCellStyle(style1);

        	cell1 = row.createCell(6); 
        	cell1.setCellValue(ae.getProName());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(7); 
        	cell1.setCellValue(ae.getGrade());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(8); 
        	cell1.setCellValue(ae.getType());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(9);
        	cell1.setCellValue(ae.getCertificationTime());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(10);
        	cell1.setCellValue(ae.getCertificationCompany());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(11);
        	cell1.setCellValue(ae.getSemester());
        	cell1.setCellStyle(style1);

        	cell1 = row.createCell(12);
        	cell1.setCellValue(ae.getPlan());
        	cell1.setCellStyle(style1);
        	i++;
        }
        
        String projectPath = req.getSession().getServletContext().getRealPath("/");
//        String tomcatPath = new File(projectPath ).getParentFile().getParentFile().getAbsolutePath();
//        String pro = req.getContextPath();
//        System.out.println("pro"+pro);
        System.out.println("projectPath  "+projectPath);
//        System.out.println("tomcatPath  "+tomcatPath);
        
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String path=projectPath+"技能竞赛.xls";
        System.out.println("path  "+path);
        
        FileOutputStream fout = new FileOutputStream(path);  
    	wb.write(fout);  
    	fout.close();
    	
    	resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
        
    	OutputStream out = resp.getOutputStream();
    	
        InputStream in = new FileInputStream(path);
        
        byte [] buffer = new byte[1024];
        int len = 0;
        
        while((len = in.read(buffer)) != -1) {
        	out.write(buffer, 0, len);
        }
        in.close();
//        //第六步将生成excel文件保存到指定路径下  
//        try {  
//            FileOutputStream fout = new FileOutputStream(path);  
//            wb.write(fout);  
//            fout.close(); 
//            tips = new Tips(0,path);
//			jsonTips = JSONObject.fromObject(tips);
//        } catch (IOException e) {  
//            e.printStackTrace();  
//            tips = new Tips(1,"error");
//			jsonTips = JSONObject.fromObject(tips);
//        }  
//        req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
//		resp.getWriter().print(jsonTips);
//		resp.getWriter().flush();
//		resp.getWriter().close();
    }  
		
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("已经注销了");
	}
	
}