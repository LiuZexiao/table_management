package com.information.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.information.entity.Tips;
import com.information.entity.UserEntity;
import com.information.service.UserinfoService;
import com.information.serviceimp.UserinfoServiceImp;

import net.sf.json.JSONObject;


public class LoginServlet extends HttpServlet {	
	static public HttpSession session = null;
	/**
	 * 
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.println("已经实例化了123");
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
		session = req.getSession();
		
		// 将 json 变成字符串
		String params = getRequestJsonString(req);
		System.out.println(params);
		// 将 json 字符串中的数据提取出来
		JSONObject jsonObject = JSONObject.fromObject(params);
	  	String workNum = jsonObject.getString("workNum");  
        String upassword = jsonObject.getString("upassword");
        
		UserinfoService lg =new UserinfoServiceImp();
		
		UserEntity log=lg.login(workNum,upassword);
		if(log!=null)
		{
			// 成功后向前端返回信息表示登陆成功
			tips = new Tips(0,"success");
			jsonTips = JSONObject.fromObject(tips);
			// 在 session 中做标记，表示登陆成功
			session.setAttribute("loginWorkNum", workNum);
			System.out.println("Login"+(String)session.getAttribute("loginWorkNum"));
		}else {
			tips = new Tips(1,"error");
			jsonTips = JSONObject.fromObject(tips);
		}
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(jsonTips);
		resp.getWriter().flush();
		resp.getWriter().close();
//		resp.sendRedirect(path);
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("已经注销了");
	}
}