package com.information.servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.information.dao.UserDao;
import com.information.entity.Tips;
import com.information.entity.UserEntity;
import com.information.impdao.UserImpDao;

import net.sf.json.JSONObject;

@MultipartConfig
public class Upload extends HttpServlet{
	private POIFSFileSystem fs;
    private Workbook wb;
    private Sheet sheet;
    private Row row;
    private File file = null;
//    private HSSFSheet sheet;
//    private HSSFRow row;
 
    UserDao userDao = new UserImpDao();
 
    public String savePath = getClass().getResource("/").getPath();
    public static String excelname;
    //上传文件保存目录
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        getFile(request, response);  //上传文件
        List<UserEntity> users = getUser(excelname);  //读取excel中的users,生成user_table.xls
 
        int exi = 0;
        exi = userDao.addAllUser(users);  //将读取的List<User>插入数据库
        
        Tips tips;
        JSONObject jsonTips;
        if(exi != 0)
		{
        	tips = new Tips(exi,"success");
		}else {
			tips = new Tips(1,"error");
		}
        request.setCharacterEncoding("UTF-8");
//      response.setContentType("text/html;charset=UTF-8");
        jsonTips = JSONObject.fromObject(tips);
        response.getWriter().print(jsonTips);
        response.getWriter().flush();
        response.getWriter().close();
        
//        PrintWriter pw = response.getWriter();
//        pw.print("<script>添加成功，一共有:"+exi+"条数据</script>");
 
    }
 
//传入文件路径读取excel，得到List<User>
    public List<UserEntity> getUser(String fileurl) throws IOException {

		try {
			InputStream is = new FileInputStream(fileurl);
	        fs = new POIFSFileSystem(is);
        	wb = new HSSFWorkbook(fs);  //支持excel 2003
		} catch (Exception e) {
			wb = new  XSSFWorkbook(new FileInputStream(file));//支持excel 2007
		}
//        wb = new HSSFWorkbook(fs);
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        
        int rowNum = sheet.getLastRowNum();
        System.out.println("excel总行数：" + (rowNum+1));
        
        System.out.println("标题为：" + row.getCell(0));
        List<UserEntity> userList = new ArrayList<UserEntity>();
        UserEntity user = null;
        int i = 0;  //控制读取数
        while (i<rowNum) {
                user = new UserEntity();
                i++;
                user.setWorkNum(sheet.getRow(i).getCell(0).getStringCellValue());
                user.setuName(sheet.getRow(i).getCell(1).getStringCellValue());
                user.setuPassword("123456");
                user.setDepartmentId((int) (sheet.getRow(i).getCell(3).getNumericCellValue()));
                user.setRoleId((int) (sheet.getRow(i).getCell(4).getNumericCellValue()));
                userList.add(user);
            }
        return userList;
    }
 
//将上传的excel文件保存为文件，可选进行备份操作。
    public void getFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
 
        request.getInputStream();
        Part part = request.getPart("excel");
        String cd = part.getHeader("Content-Disposition");
        String[] cds = cd.split(";");
        String filename = cds[2].split("=")[1].replace("\"","");
        System.out.println("文件名:"+filename);
        String projectPath = request.getSession().getServletContext().getRealPath("/");
        InputStream is = part.getInputStream();
        filename = projectPath+filename;
        System.out.println("路径:"+filename);
        excelname = filename;
        file = new File(filename);
        System.out.println("file"+file);
        FileOutputStream fos = new FileOutputStream(file); //生成excel文件
        byte[] b = new byte[1024];
        int len = 0;
        while((len = is.read(b)) != -1){
            fos.write(b,0,len);
        }
        is.close();
        fos.close();
 
 
    }

}
