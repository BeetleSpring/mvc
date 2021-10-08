package mvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 这是升级出来的"小总管"
 *
 * 这个小总管对象就是原来的Servlet
 * 是遵循原来Servlet对象生命周期机制  init  service  destroy
 */
public class DispatcherServlet extends HttpServlet {

    //来一个小弟作为属性  has-a
    private Handler handler = new Handler();

    //init方法 标识着当前这个""对象的创建
    public void init(ServletConfig config){
        boolean flag = handler.loadPropertiesFile();//读取文件中 请求名--真实类全名对应关系 存起来的只是String
        String packageNames = null;
        //有可能文件是不存在---->文件中只有一个信息(告知需要扫描注解的包)
        if(!flag){//根本就没有文件 必然要扫描注解
            packageNames = config.getInitParameter("scanPackage");
        }else {
            packageNames = handler.getScanPackageName();
        }
        handler.scanAnnotation(packageNames);//扫描类 读取注解  请求名--真实类全名对应关系
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //0.通过request获取请求的类名  uri      request.getRequestURI();
            String uri = request.getRequestURI();
            //1.找1号小弟  帮助解析urlName  得到一个请求名
            String requestContent = handler.parseURI(uri);///xxx.do   类名.do  方法名.do
            //  通过request获取请求方法名  method       request.getParameter();
            String methodName = request.getParameter("method");
            if(methodName==null){
                methodName = requestContent.substring(0,requestContent.indexOf("."));
            }
            //以下开始找小弟
            //2.找2号小弟  帮助找到obj对象
            Object obj = handler.findObject(requestContent);
            //3.找3号小弟  通过obj对象找到对象里面的方法
            Method method = handler.findMethod(obj,methodName);
            //4.找4号小弟  处理方法上面的参数DI--->>>>
            Object[] finalParamValue = handler.injectionParameters(method,request,response);
            //5.直接invoke反射执行方法啦
            Object methodResult = method.invoke(obj,finalParamValue);
            //6.找5号小弟  处理方法执行完毕后的返回结果(响应 转发路径 重定向路径 返回对象JSON)
            handler.finalResolver(obj,method,methodResult,request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (java.lang.NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
