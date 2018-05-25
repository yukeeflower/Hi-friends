package com.ch.dao;

import com.ch.model.Favor;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 2018/2/28.
 */
@Mapper
@Repository
public interface FavorDAO {

    @Select({"select * from Favor where id = #{id}"})
    Favor selectFavorById(int id);

    @Insert({"insert into favor(user_id,forum_id,create_time) values(#{userId},#{forumId},#{createTime})"})
    int insertFavor(Favor favor);

    @Select({"select * from favor where user_id = #{userId} order by create_time desc"})
    List<Favor> getFavorsByUserId(int userId);

    @Select({"select * from favor where forum_id = #{0} and user_id = #{1}"})
    Favor getFavorByForumIdAndUserId(int forumId,int userId);
}
