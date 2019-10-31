package pers.swo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.swo.pojo.User;
import pers.swo.services.LoginService;
import pers.swo.services.impl.LoginServiceImpl;

public class LoginServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式:
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        //获取请求信息
        String uname=req.getParameter("uname");
        //uname=new String(uname.getBytes("iso8859-1"),"utf-8");//使用String进行数据重新编码
        String pwd=req.getParameter("pwd");
        System.out.println(uname+":"+pwd);
        //处理请求信息
        //获取业务层对象
        LoginService ls=new LoginServiceImpl();
        User u=ls.checkLoginService(uname, pwd);
        System.out.println(u);
        //响应处理结果
        if (u != null) {
//            resp.getWriter().write("登录成功");
            // 创建cookie信息实现三天免登录
            Cookie c = new Cookie("uid", u.getUid() + "");
            // 设置cookie的有效期
            c.setMaxAge(3*24*3600);
            c.setPath("ck");
            resp.addCookie(c);
            //请求转发
//            req.getRequestDispatcher("main").forward(req, resp);
            //重定向
            resp.sendRedirect("main");
        } else {
//            resp.getWriter().write("登录失败");
            // 使用request对象的attribute实现不同Servlet的数据流转，一次请求周期内有效
            req.setAttribute("str", "用户名或密码错误");
            // 请求转发
            req.getRequestDispatcher("page").forward(req, resp);
        }
    }
}
