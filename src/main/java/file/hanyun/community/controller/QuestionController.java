package file.hanyun.community.controller;

import file.hanyun.community.dto.QuestionDTO;
import file.hanyun.community.mapper.QuestionMapper;
import file.hanyun.community.service.QuestionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * author:hanyun.li
 * createTime:2019/11/29
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionDTOService questionDTOService;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/question/{id}")
    public String toQuestion(@PathVariable("id") Integer id,
                             Model model){
        Integer viewCount = questionMapper.queryViewCountById(id);
        if (viewCount != null){
            ++viewCount;
            questionMapper.updateByViewCount(viewCount,id);
        }
        QuestionDTO questionDTO = questionDTOService.getById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
