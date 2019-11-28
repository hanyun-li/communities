package file.hanyun.community.controller;

import file.hanyun.community.dto.PaginationDTO;
import file.hanyun.community.entity.Question;
import file.hanyun.community.entity.User;
import file.hanyun.community.service.MyQuestionDTOService;
import file.hanyun.community.service.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private MyQuestionDTOService myQuestionDTOService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "currentPage",defaultValue = "1") Integer currentPage,
                          @RequestParam(name = "size",defaultValue = "5") Integer size){

        User user = (User)request.getSession().getAttribute("user");
        Integer id = user.getId();
        PaginationDTO pagination = myQuestionDTOService.getMyAllQuestionDTOInfo(id,currentPage,size);
        model.addAttribute("pagination",pagination);

        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }

}
