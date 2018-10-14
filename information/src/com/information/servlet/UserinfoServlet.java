package com.information.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.information.entity.Tips;
import com.information.entity.UserEntity;
import com.information.service.UserinfoService;
import com.information.serviceimp.UserinfoServiceImp;

import net.sf.json.JSONObject;

public class UserinfoServlet extends HttpServlet{

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.println("已经实例化了");
	}
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Tips tips;
		JSONObject jsonTips;
		String workNum = (String) LoginServlet.session.getAttribute("loginWorkNum");
		System.out.println(workNum);
		UserinfoService lg =new UserinfoServiceImp();
		UserEntity log=lg.myUserinfo(workNum);
		
		if(log!=null)
		{
			// 将用户信息转换成 json 格式传回
			jsonTips = JSONObject.fromObject(log);
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
