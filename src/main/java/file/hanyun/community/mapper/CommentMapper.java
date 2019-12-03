package file.hanyun.community.mapper;

import file.hanyun.community.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper {

    @Insert("insert into comment(id,parent_id,type,commentator,gmt_create,gmt_modified,like_count,content) values(#{id},#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    void insertByComment(Comment comment);
}