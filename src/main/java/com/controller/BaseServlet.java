package com.controller;

import com.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BaseServlet实现：让一个servlet对应多个方法，每个方法都实现一个业务逻辑
 *
 * 具体的实现思路：
 *      1. 在一个servlet声明多个处理业务逻辑的方法
 *      2. 在每次访问servlet的时候在路径上添加一个标识，用于判断要访问的具体业务逻辑方法
 *      3. 当每次请求到doGet/doPost/service方法的时候，判断参数的标识，调用对应的业务逻辑方法
 *
 * 优化多Servlet实现
 *      创建一个BaseServlet类，让它去继承HttpServlet
 *      BaseServlet中写service方法
 *      在service写1.获取标识 2.反射调用业务逻辑
 *      每个模块对应的Controller只需要集成BaseServlet即可
 *
 */

/*
 * UserController对应的不是某一个具体的方法和功能
 * 它要实现的是用户模块的所有业务逻辑，例如：登录、注册
 * */

// 1. 接受请求的标识符
// 标识符的规定，当每次请求，用户需要在请求参数声明method参数
// 参数的值，就是我们要调用的方法名
// 例如： /user?method=登录方法名
//       /user?method=注册的方法名

/*
* 每个方法都要进行响应
* 响应的方式固定：转发 重定向 返回字符串 返回字节流
* 如果在每个方法写转发和重定向和返回字符串的语法比较繁琐
* 我们可以统一在BaseServlet进行处理
*
* 操作：
*       1. 将方法的返回值改成字符串即可
*       2. 根据约定的内容，添加特殊的标识
*           例如：转发"forward: 路径"
*       3. BaseServlet集中处理
*           执行方法 获取返回值 进行非空判断
*           截取标识 进行转发重定向或者写回字符串处理
* 好处： 简化方法的响应的操作
* 注意： 没有管返回字节
*       我们只需要将方法的返回值改回void,只需要使用response对象自己动手操作即可
* */

/*
* BaseServlet用于集中处理方法的调用以及返回值处理以及默认页对应方法
* */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 获取请求参数 (标识符)
        String methodStr = req.getParameter(Constants.TAG);

        // 2. 如果method没有获取到值，我们就跳转到首页 (标识符异常处理)
        if (methodStr == null && methodStr.equals("")) {
            methodStr = Constants.INDEX;
        }

        // 3. 反射调用对应的业务逻辑方法
        Class clazz = this.getClass();
        try {
            Method method = clazz.getMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
            Object result = method.invoke(this, req, resp);

            // 4. 集中处理返回值响应
            if (result != null){
                String str = (String)result;
                if (str.startsWith(Constants.FORWARD)){
                    // 转发
                    String path = str.substring(str.indexOf(Constants.FLAG)+1);
                    req.getRequestDispatcher(path).forward(req, resp);
                }else if (str.startsWith(Constants.REDIRECT)){
                    // 重定向
                    String path = str.substring(str.indexOf(Constants.FLAG)+1);
                    resp.sendRedirect(path);
                }else {
                    resp.getWriter().println(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // controller 和 service dao 有异常都会到此处
            req.getSession().setAttribute("msg", "程序异常！请稍后再试！");
            resp.sendRedirect("/message.jsp");
        }
    }

    /*
    * 当method标识符没有值，我们默认index访问每个controller的index方法
    * 我们将方法提取到baseServlet中即可
    * 默认处理：跳转到程序的首页
    * */
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().println("注册业务逻辑");
//        request.getRequestDispatcher("路径").forward(request, response);
        return Constants.FORWARD + "/index.jsp";
    }
}
