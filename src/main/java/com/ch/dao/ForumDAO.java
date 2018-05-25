package com.ch.dao;

import com.ch.model.Forum;
import com.ch.model.Userinfo;
import org.apache.ibatis.annotations.*;
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

    @Select({"select * from forum where user_id = #{userId} order by id desc"})
    List<Forum>selectForumsByUserId(int userId);

    @Insert({"insert into forum(title,content,pictures,user_id,create_time,scan_num,favor_num,remark_num,tags) values" +
            "(#{title},#{content},#{pictures},#{userId},#{createTime},#{scanNum},#{favorNum},#{remarkNum},#{tags})"})
    int insertForum(Forum forum)throws Exception;

    @Update({"update forum set pictures = #{pics} where id = #{id}"})
    int updateForumPics(@Param("pics") String pics, @Param("id") int id)throws Exception;

    @Update({"<script>update forum set " +
            "<trim suffixOverrides=\",\">"+
            "<if test=\"title != null \"> title = #{title},</if>" +
            "<if test=\"content != null \"> password = #{password},</if>" +
            "<if test=\"pictures != null \"> nickname = #{nickname},</if>" +
            "<if test=\"createTime != null \">create_time = #{createTime},</if> " +
            "<if test=\"scanNum != null \">scan_num = #{scanNum} ,</if>"+
            "<if test=\"favorNum != null \">favor_num = #{favorNum} ,</if>"+
            "<if test=\"remarkNum != null \">remark_num = #{remarkNum} </if>"+
            "<if test=\"tags != null \">tags = #{tags} </if>"+
            "</trim>"+
            "where id = #{id} </script>"})
    int updateForumSelective(Forum forum );

}
