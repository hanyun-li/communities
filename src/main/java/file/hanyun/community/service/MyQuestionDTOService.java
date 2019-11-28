package file.hanyun.community.service;

import file.hanyun.community.dto.PaginationDTO;

public interface MyQuestionDTOService {
    PaginationDTO getMyAllQuestionDTOInfo(Integer creator,Integer page, Integer size);
}
