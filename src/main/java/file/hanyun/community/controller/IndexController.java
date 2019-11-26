package file.hanyun.community.controller;

import file.hanyun.community.dto.PaginationDTO;
import file.hanyun.community.service.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private QuestionDTOService questionDTOService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "currentPage",defaultValue = "1") Integer currentPage,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){

        PaginationDTO pagination = questionDTOService.getAllQuestionDTOInfo(currentPage,size);
        model.addAttribute("pagination",pagination);

        return "index";
    }

}
