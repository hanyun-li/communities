package file.hanyun.community.service;


import file.hanyun.community.dto.QuestionDTO;

import java.util.List;

public interface QuestionDTOService {
    List<QuestionDTO> getAllQuestionDTOInfo(Integer page, Integer size);
}
