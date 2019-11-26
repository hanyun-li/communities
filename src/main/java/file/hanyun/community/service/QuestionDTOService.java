package file.hanyun.community.service;


import file.hanyun.community.dto.PaginationDTO;

public interface QuestionDTOService {
    PaginationDTO getAllQuestionDTOInfo(Integer page, Integer size);
}
