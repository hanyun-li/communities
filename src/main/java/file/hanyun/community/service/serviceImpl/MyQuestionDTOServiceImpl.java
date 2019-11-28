package file.hanyun.community.service.serviceImpl;

import file.hanyun.community.dto.PaginationDTO;
import file.hanyun.community.dto.QuestionDTO;
import file.hanyun.community.entity.Question;
import file.hanyun.community.entity.User;
import file.hanyun.community.mapper.QuestionMapper;
import file.hanyun.community.mapper.UserMapper;
import file.hanyun.community.service.MyQuestionDTOService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyQuestionDTOServiceImpl implements MyQuestionDTOService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PaginationDTO getMyAllQuestionDTOInfo(Integer creator, Integer page, Integer size) {
        Integer offset = size * (page -1);  //获取当前页的第一个记录数索引值
        Integer myRecordCount = questionMapper.selectMyTatolRecordCount(creator);  //我发布的问题的所有记录数
        List<Question> myQuestions = questionMapper.getMyAllQuestionInfo(creator,offset,size);  //获取我发布的所有问题
        List<QuestionDTO> myQuestionDTOS = new ArrayList();
        PaginationDTO myPaginationDTO = new PaginationDTO();
        for (Question myQuestion: myQuestions) {
            User user = userMapper.findById(myQuestion.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(myQuestion,questionDTO);
            questionDTO.setUser(user);
            myQuestionDTOS.add(questionDTO);
        }
        myPaginationDTO.setQuestions(myQuestionDTOS);
        myPaginationDTO.setPagination(myRecordCount,size,page);
        return myPaginationDTO;
    }
}
