package file.hanyun.community.controller;

import file.hanyun.community.dto.QuestionDTO;
import file.hanyun.community.service.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionDTOService questionDTOService;

    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable("id") Integer id,
                             Model model){

        QuestionDTO questionDTO = questionDTOService.getById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
