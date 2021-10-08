package mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个类是后包装而成的
 * 目的是为了将两部分信息包在一起
 * 一个是存储的数据     Map<String,Object>
 * 一个是响应的视图名字  String
 */
public class ModelAndView {

    private String viewName;//响应信息 视图的名字
    private HashMap<String,Object> attributeMap = new HashMap();

    //以下两个方法给用户使用
    //第一个是为了给用户存储 最终转发路径/视图名字
    public void setViewName(String viewName){
        this.viewName = viewName;
    }
    //第二个是为了给用户 每次向map集合内存储一组key-value
    public void addObject(String key,Object value){
        this.attributeMap.put(key,value);
    }

    //======================================================
    //以下提供给框架 用来获取信息
    String getViewName(){
        return this.viewName;
    }
    Object getObject(String key){
        return this.attributeMap.get(key);
    }
    HashMap<String,Object> getAttributeMap(){
        return this.attributeMap;
    }

}
