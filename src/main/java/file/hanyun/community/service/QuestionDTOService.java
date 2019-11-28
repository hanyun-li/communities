package file.hanyun.community.service;


import file.hanyun.community.dto.PaginationDTO;
import file.hanyun.community.dto.QuestionDTO;

public interface QuestionDTOService {
    PaginationDTO getAllQuestionDTOInfo(Integer page, Integer size);

    QuestionDTO getById(Integer id);
}
