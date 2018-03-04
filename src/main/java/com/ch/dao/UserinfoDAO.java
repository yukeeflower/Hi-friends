package com.ch.dao;
import org.apache.ibatis.annotations.*;
import com.ch.model.Userinfo;
import org.springframework.stereotype.Repository;
import java.util.Date;

/**
 * Created by apple on 2018/2/7.
 */
@Repository
@Mapper
public interface UserinfoDAO {

    @Select({"select * from Userinfo where id = #{id}"})
    Userinfo selectById(int id);


    @Select({"select * from Userinfo where username = #{username}"})
    Userinfo selectByUsername(String username);

    @Update({"update userinfo set password = #{password},last_modify = #{lastModify} where id = #{id}"})
    int updatePasswordById(@Param("id") int id , @Param("password") String password, @Param("lastModify")Date lastModify);


//    @Update({"update userinfo set nickname = #{nickname},money = #{money},email = #{email},last_modify = #{lastModify} where id = #{0}"})
//    int updateUserinfo(int id ,@Param("nickname") String nickname,@Param("money") int money,@Param("email") String email,@Param("lastModify") Date lastModify);

    @Update({"<script>update userinfo set " +
            "<trim suffixOverrides=\",\">"+
            "<if test=\"username != null \"> username = #{username},</if>" +
            "<if test=\"password != null \"> password = #{password},</if>" +
            "<if test=\"nickname != null \"> nickname = #{nickname},</if>" +
            "<if test=\"salt != null \"> salt = #{salt},</if>" +
            "<if test=\"email != null \">email = #{email},</if> " +
            "<if test=\"money != null \">money = #{money} ,</if>"+
            "<if test=\"createTime != null \">create_time = #{createTime} ,</if>"+
            "<if test=\"lastModify != null \">last_modify = #{lastModify} </if>"+
            "<if test=\"headUrl != null \">head_url = #{headUrl} </if>"+
            "</trim>"+
            "where id = #{id} </script>"})
    int updateUserinfoSelective(Userinfo userinfo );

    @Insert({"insert into userinfo(username,password,salt,nickname,money,email,create_time,last_modify,status,head_url) " +
            "values(#{username},#{password},#{salt},#{nickname},#{money},#{email},#{createTime},#{lastModify},#{status},#{headUrl})"})
    int insertUser(Userinfo userinfo);
}
