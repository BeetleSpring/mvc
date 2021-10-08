package controller;

import domain.User;
import mvc.*;
import service.AtmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//松耦合
//  继承父类                现在不用继承
//  方法重写                现在方法不用重写了
//  方法必须有两个参数        现在方法参数可以随意    原生类型String int float Float HttpServletRequest(不推荐) 数组不行 接口不行 List/Set不行
//  方法必须有两个异常        现在方法是没有异常的
//  方法没有返回值           现在方法是有返回值  String 表示viewName 视图的路径(转发、重定向redirect:)
//  Servlet对象的生命周期问题-----管理机制
//  单例 延迟加载           底层的Controller对象单例机制 延迟加载的机制还保留

@SessionAttributes("name")
public class AtmController {//需要管理这个Controller的单例机制

    private AtmService service = new AtmService();

    //去做刚才LoginController里面该做的事情
    @RequestMapping("login.do")
    public ModelAndView login(User user){
        ModelAndView mv = new ModelAndView();
        //1.接收请求参数----->以前通过request来接的 现在有参数列表啦
        //2.找寻业务层做登录逻辑判断
        String result = service.login(user);
        //3.根据result做响应转发
        if("success".equals(result)){
            //我想要参数值  需要放一个空的容器(参数)  让框架去接收值  注入到参数容器里
            //我想将值存入request作用域  写一个容器 值存入容器里  让框架去容器里拿值  存入request里
            //想要将值存入request  写一个容器 我们的值存入容器里
            //容器是什么类型的呢?  Map<String,Object>
            //这个map如何交给框架???  返回值   对象(map+viewName)
            mv.addObject("name",user.getName());//如果存在session中 先放在mv容器里
            mv.setViewName("welcome.jsp");
        }else{
            mv.addObject("result",result);
            mv.setViewName("index.jsp");
        }
        return mv;





        //1.接收请求传递的参数???
        //我想要接收name参数
        //原来是我们自己通过request对象获取
        //现在我想要参数 但是不主动获取 放置一个变量 让小总管帮我自动注入IOC DI
        //2.调用业务层的方法
        //String result = service.login(name);
        //3.给予响应
        //  自己给响应
        //response.getWriter().write("<html>");
//        return "welcome.jsp";//     约定优于配置的方式
        //response.sendRedirect("xxx.jsp");
    }

    //去做刚才QueryController里面该做的事情
    @ResponseBody
    @RequestMapping("query.do")
    public List<User> query(){
        System.out.println("我是query的Controller");
        //自己的取值
        //给框架一个map集合 作为返回值 key value
        //让框架获取map集合 从里面获取值  request.setAttribute();
        //自己的调用业务
        //自己的响应
        //自己给响应 write       HTML+AJAX
        //List<User> userList = service.query();
        //JSON
        return null;//这个返回值表示什么含义???  转发/重定向 路径 资源名   ||  仅仅表示一个响应数据
    }

}
