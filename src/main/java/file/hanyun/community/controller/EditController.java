package file.hanyun.community.controller;

import file.hanyun.community.entity.Question;
import file.hanyun.community.entity.User;
import file.hanyun.community.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EditController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       HttpServletRequest request,
                       Model model){
        Question question = questionMapper.getById(id);
        String questionTitle = question.getTitle();
        String questionDescription = question.getDescription();
        String questionTag = question.getTag();
        model.addAttribute("title",questionTitle);
        model.addAttribute("description",questionDescription);
        model.addAttribute("tag",questionTag);
        request.getSession().setAttribute("questionId",id);
        return "edit";
    }

    @PostMapping("/do_edit")
    public String doEdit(@RequestParam String title,
                         @RequestParam String description,
                         @RequestParam String tag,
                         HttpServletRequest request,
                         Model model){

        //回显
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if (title == null || title.equals("") ){
            model.addAttribute("titleError","标题不能为空");
            return "edit";
        }
        if (description == null || description.equals("")){
            model.addAttribute("descriptionError","描述不能为空");
            return "edit";
        }
        if (tag == null || tag.equals("")){
            model.addAttribute("tagError","标签不能为空");
            return "edit";
        }
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error","用户未登录");
            return "edit";
        }

        Integer questionId = (Integer)request.getSession().getAttribute("questionId");
        Question question = questionMapper.getById(questionId);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        questionMapper.updateById(question);

        return "redirect:/";
    }
}
