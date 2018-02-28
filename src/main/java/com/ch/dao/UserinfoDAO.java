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
    Userinfo getUserinfoById(int id);


    @Select({"select * from Userinfo where username = #{username}"})
    Userinfo getUserinfoByUsername(String username);

    @Update({"update userinfo set password = #{password},last_modify = #{lastModify} where id = #{id}"})
    int updatePasswordById(@Param("id") int id , @Param("password") String password, @Param("lastModify")Date lastModify);


    @Update({"update userinfo set nickname = #{nickname},money = #{money},email = #{email},last_modify = #{lastModify} where id = #{0}"})
    int updateUserinfo(int id ,@Param("nickname") String nickname,@Param("money") int money,@Param("email") String email,@Param("lastModify") Date lastModify);

    @Update({"<script>update userinfo set " +
            "<if test=\"nickname != null \"> nickname = #{nickname}</if>," +
            "<if test=\"email != null \"> email = #{email}</if> " +
            "where id = #{id} </script>"})
    int updateUserinfoSelective(@Param("id") int id ,@Param("nickname") String nickname,@Param("email") String email );

    @Insert({"insert into userinfo(username,password,salt,nickname,money,email,create_time,last_modify) " +
            "values(#{username},#{password},#{salt},#{nickname},#{money},#{email},#{createTime},#{lastModify})"})
    int insertUser(Userinfo userinfo);
}
