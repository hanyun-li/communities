package file.hanyun.community.controller;

import file.hanyun.community.dto.CommentDTO;
import file.hanyun.community.dto.ResultDTO;
import file.hanyun.community.entity.Comment;
import file.hanyun.community.entity.User;
import file.hanyun.community.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(@RequestBody CommentDTO commentDTO,
                          HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            ResultDTO resultDTO = ResultDTO.errorOf("-1", "failed");
            return resultDTO;
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentMapper.insertByComment(comment);
        Map<Object,Object> objectMap = new HashMap<>();
        objectMap.put("message","success");
        return objectMap;
    }
}
