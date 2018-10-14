package com.information.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.information.entity.Tips;
import com.information.service.ScientificService;
import com.information.serviceimp.ScientificServiceImp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class DeleteScientificServlet extends HttpServlet {	
	/**
	 * 
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.println("已经实例化了DeleteScientificServlet");
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
		JSONObject jsonobj;
		JSONArray jsonArray = null;
		
		// 将 json 变成字符串
		String params = getRequestJsonString(req);
		System.out.println(params);
		// 将 json 字符串中的数据提取出来
		jsonobj = JSONObject.fromObject(params);
		jsonArray =jsonobj.getJSONArray("id");//获取数组
		int jsonLength = (int)jsonobj.get("jsonLength");//获取长度
		System.out.println(jsonArray);
		System.out.println(jsonLength);
		List list = new ArrayList();
		
		for(int i = 0; i < jsonLength; i++) {
			list.add((int) jsonArray.get(i));
		}			
		
		ScientificService lg =new ScientificServiceImp();
		int log = lg.deleteScientific(list);
		
		if(log!=0)
		{
			tips = new Tips(0,"success");
			jsonTips = JSONObject.fromObject(tips);
		}else {
			tips = new Tips(1,"error");
			jsonTips = JSONObject.fromObject(tips);
		}
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
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