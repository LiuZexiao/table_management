package com.information.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.information.entity.AwardEntity;
import com.information.entity.PageEntity;
import com.information.entity.Tips;
import com.information.service.PageService;
import com.information.serviceimp.PageImpService;

import net.sf.json.JSONObject;

public class AwardPageServlet extends HttpServlet{
	/**
	 * 
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.println("已经实例化了SelectUserServlet1");
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
		JSONObject jsonTips;
		JSONObject jsonObject;
		PageEntity<AwardEntity> award = null;
		
		// 将 json 变成字符串
		String params = getRequestJsonString(req);
		System.out.println(params);
		// 将 json 字符串中的数据提取出来
		jsonObject = JSONObject.fromObject(params);
	  	int pageNum = (int)jsonObject.get("pageNum");
	  	int pageSize = (int)jsonObject.get("pageSize");
	  	int departmentId = (int)jsonObject.get("departmentId");
	  	String identity = (String)jsonObject.get("identity");
	  	String type = (String)jsonObject.get("type");
	  	String rank = (String)jsonObject.get("rank");
	  	String clock1 = (String)jsonObject.get("clock1");
	  	String clock2 = (String)jsonObject.get("clock2");
	  	String uName = (String)jsonObject.get("uName");
	  	int userinfoId = (int)jsonObject.get("userinfoId");
	  	
	  	int pageNo = (pageNum-1)*pageSize;
	  	PageService lg =new PageImpService();
	  	award = lg.awardinfo(pageNo, pageSize, new Object[] {departmentId, identity, type, rank, clock1, clock2, uName, userinfoId});
		System.out.println(award);
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("tableData", award.getList());
		map.put("totalCount", award.getTotalPageNumber()*pageSize);
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		jsonTips = JSONObject.fromObject(map);
		System.out.println("jsonTips"+jsonTips);
		resp.getWriter().print(jsonTips);
		
		resp.getWriter().flush();
		resp.getWriter().close();
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("已经注销了");
	}
}
