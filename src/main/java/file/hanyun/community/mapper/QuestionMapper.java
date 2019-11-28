package file.hanyun.community.mapper;

import file.hanyun.community.entity.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> getAllQuestionInfo(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer selectTatolRecordCount();

    @Select("select count(1) from question where creator = #{creator}")
    Integer selectMyTatolRecordCount(@Param("creator") Integer creator);

    @Select("select * from question where creator = #{creator} limit #{offset},#{size}")
    List<Question> getMyAllQuestionInfo(@Param("creator") Integer creator, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);

    @Update("update question set title = #{title},description = #{description},tag = #{tag} where id = #{id}")
    void updateById(Question question);
}
