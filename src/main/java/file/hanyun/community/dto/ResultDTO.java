package file.hanyun.community.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private String code;
    private String message;

    public static ResultDTO errorOf(String code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }
}
