package file.hanyun.community.service.serviceImpl;

import file.hanyun.community.dto.PaginationDTO;
import file.hanyun.community.dto.QuestionDTO;
import file.hanyun.community.entity.Question;
import file.hanyun.community.entity.User;
import file.hanyun.community.exception.CustomizeErrorCode;
import file.hanyun.community.exception.CustomizeException;
import file.hanyun.community.mapper.QuestionMapper;
import file.hanyun.community.mapper.UserMapper;
import file.hanyun.community.service.QuestionDTOService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionDTOServiceImpl implements QuestionDTOService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @param page 当前页的页码数
     * @param size 一页显示多少条记录
     * @return List
     */
    @Override
    public PaginationDTO getAllQuestionDTOInfo(Integer page, Integer size) {

        Integer offset = size * (page -1);  //获取当前页的第一个记录数索引值
        Integer recordCount = questionMapper.selectTatolRecordCount();  //所有记录数

        List<Question> questions = questionMapper.getAllQuestionInfo(offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question: questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        paginationDTO.setPagination(recordCount,size,page);
        return paginationDTO;
    }

    @Override
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }
}
