package com.information.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import com.information.entity.ScientificEntity;
import com.information.entity.Tips;
import com.information.service.ScientificService;
import com.information.serviceimp.ScientificServiceImp;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.mysql.fabric.Response;

import net.sf.json.JSONObject;


public class OutPutScientificServlet extends HttpServlet {	
	/**
	 * 
	 */
	@Override
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
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Tips tips;
		JSONObject jsonTips;
		JSONObject jsonObject;
		List<ScientificEntity> scientific = null;
		
		// 将 json 变成字符串
		String params = getRequestJsonString(req);
		System.out.println(params);
		// 将 json 字符串中的数据提取出来
		jsonObject = JSONObject.fromObject(params);
	  	int departmentId = (int)jsonObject.get("departmentId");
	  	int categoryId = (int)jsonObject.get("categoryId");
	  	String rank = (String)jsonObject.get("rank");
	  	String clock1 = (String)jsonObject.get("clock1");
	  	String clock2 = (String)jsonObject.get("clock2");
	  	String uName = (String)jsonObject.get("uName");
	  	int userinfoId = (int)jsonObject.get("userinfoId");
	  	
	  	ScientificService lg =new ScientificServiceImp();
	  	scientific = lg.scientificinfo(new Object[] {departmentId, categoryId, rank, clock1, clock2, uName, userinfoId});
		System.out.println(scientific);
		
		//第一步创建workbook  
        HSSFWorkbook wb = new HSSFWorkbook();
          
        //第二步创建sheet  
        HSSFSheet sheet = wb.createSheet("sheet1");  
        
        //第三步创建行row:添加表头0行  
        
        //合并单元格 参数说明：1：开始行 2：结束行  3：开始列 4：结束列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));
        HSSFRow Hrow = sheet.createRow(0); 
        Hrow.setHeightInPoints(50);
        HSSFCellStyle  Hstyle = wb.createCellStyle();  
        Hstyle.setAlignment(HorizontalAlignment.CENTER);
        Hstyle.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont Hfont=wb.createFont();
        Hfont.setFontName("黑体");  
        Hfont.setFontHeightInPoints((short) 22);//设置字体大小   
        Hstyle.setFont(Hfont);//选择创建的字体格式 
        HSSFCell Hcell = Hrow.createCell(0); //第一个单元格  
        Hcell.setCellValue("南京机电职业技术学院科研成果汇总表");  
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
        font.setFontHeightInPoints((short) 12);//设置字体大小   
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
        cell.setCellValue("成果类别");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(2, 4000);
        
        cell = row.createCell(3);         
        cell.setCellValue("科研成果名称");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(3, 10000);
        
        cell = row.createCell(4);         
        cell.setCellValue("成果来源");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(4, 6000);
        
        cell = row.createCell(5);         
        cell.setCellValue("成果级别");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(5, 4000);
          
        cell = row.createCell(6);         
        cell.setCellValue("取得日期");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(6, 6000);
       
        cell = row.createCell(7);         
        cell.setCellValue("参与情况");  
        cell.setCellStyle(style); 
        sheet.setColumnWidth(7, 4000);
        
        
        //第五步插入数据  
        Iterator<ScientificEntity> iter = scientific.iterator();
        int i = 0;
        while(iter.hasNext()) {
        	ScientificEntity se = iter.next();
        	
        	row = sheet.createRow(i+2);
        	
        	HSSFCell cell1 = row.createCell(0); 
        	cell1.setCellValue(i+1); 
        	cell1.setCellStyle(style1);

        	cell1 = row.createCell(1); 
        	cell1.setCellValue(se.getuName());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(2); 
        	cell1.setCellValue(se.getCategoryName());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(3); 
        	cell1.setCellValue(se.getProName());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(4); 
        	cell1.setCellValue(se.getProSource());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(5);
        	cell1.setCellValue(se.getRank());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(6);
        	cell1.setCellValue(se.getCertificationTime());
        	cell1.setCellStyle(style1);
        	
        	cell1 = row.createCell(7);
        	cell1.setCellValue(se.getSituation());
        	cell1.setCellStyle(style1);
        	
        	i++;
        }
        // 项目的绝对路径
        String projectPath = req.getSession().getServletContext().getRealPath("/");
        // tomcat 绝对路径
//        String tomcatPath = new File(projectPath ).getParentFile().getParentFile().getAbsolutePath();
        // 项目相对路径
//        String pro = req.getContextPath();
       
        System.out.println("projectPath  "+projectPath);
//        System.out.println("tomcatPath  "+tomcatPath);
//        System.out.println("pro  "+pro);
        
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String fileName = "科研获奖.xls";
        String path=projectPath+fileName;
        //String path1 =File.separator+"information"+File.separator+fileName;
        System.out.println("path  "+path);
//        System.out.println("path1  "+path1);
        //第六步将生成excel文件保存到指定路径下  
   
        	FileOutputStream fout = new FileOutputStream(path);  
        	wb.write(fout);  
        	fout.close();
        	
//        	resp.setContentType("application/vnd.ms-excel");
            resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
//            resp.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));

//            resp.setHeader("Content-Disposition", "attachment; filename=kpi.xlsx");
        	OutputStream out = resp.getOutputStream();
        	
            InputStream in = new FileInputStream(path);
            
            byte [] buffer = new byte[1024];
            int len = 0;
            
            while((len = in.read(buffer)) != -1) {
            	out.write(buffer, 0, len);
            }
            in.close();
        
//        SmartUpload su=new SmartUpload();
//        su.initialize(getServletConfig(), req, resp);
//        su.setContentDisposition(null);
//        try {
//			su.downloadFile(fileName);
//		} catch (SmartUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//            resp.sendRedirect(path);
//            tips = new Tips(0,path);
//			jsonTips = JSONObject.fromObject(tips);
//        } catch (IOException e) {  
//            e.printStackTrace();  
//            tips = new Tips(1,"error");
//			jsonTips = JSONObject.fromObject(tips);
//        }  
//		resp.getWriter().print(jsonTips);
//		resp.getWriter().flush();
//		resp.getWriter().close();
    }  
	@Override	
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("已经注销了");
	}
	
}