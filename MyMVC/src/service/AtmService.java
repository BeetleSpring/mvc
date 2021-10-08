package service;

import domain.User;

public class AtmService {

    //业务逻辑方法
    public String login(User user){
        //真实的操作应该依赖dao的数据
        if("laozei".equals(user.getName()) && 123==user.getPass()){
            return "success";
        }
        return "defeat";
    }
}
