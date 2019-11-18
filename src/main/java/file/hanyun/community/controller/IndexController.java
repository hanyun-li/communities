package file.hanyun.community.controller;

import file.hanyun.community.dto.QuestionDTO;
import file.hanyun.community.entity.User;
import file.hanyun.community.mapper.UserMapper;
import file.hanyun.community.service.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;  //该报错可以忽视

    @Autowired
    private QuestionDTOService questionDTOService;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null&&cookies.length != 0){
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

            List<QuestionDTO> questionDTOS = questionDTOService.getAllQuestionDTOInfo(page,size);
            model.addAttribute("questions",questionDTOS);

        }
        return "index";
    }

}
