SpringMVC启动流程
1. Tomcat启动
2. 解析web.xml listener 创建Spring容器(父容器) => 存入servletContext
3. DispatcherServlet实例化
4. DispatcherServlet对象.init() 从servletContext获取父容器 => 创建Spring容器(子容器) => setParent()完成绑定
// 对于servlet可以指定额外动作 HttpServletBean.init()
// 实际调用了FrameworkServlet中的initWebApplicationContext()来初始化spring容器
// getContextConfigLocation()是获取配置文件的方法，获取后执行refresh
5. 接受请求