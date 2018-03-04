package com.ch.dao;

import com.ch.model.Forum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by apple on 2018/2/28.
 */
@Mapper
@Repository
public interface ForumDAO {

    @Select({"select * from forum where id = #{id}"})
    Forum selectForumById(int id);

    @Select({"select * from forum order by id desc"})
    List<Forum>selectLatestForums();

}
