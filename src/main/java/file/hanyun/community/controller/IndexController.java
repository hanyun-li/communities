package file.hanyun.community.controller;

import file.hanyun.community.entity.User;
import file.hanyun.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;  //该报错可以忽视

    @GetMapping("/")
    public String idnex(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                //如果数据库中存在用户登录的token，则保持登录状态
                if(user != null){
                    httpServletRequest.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";

    }

}
