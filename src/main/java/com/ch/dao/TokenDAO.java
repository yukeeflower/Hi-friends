package com.ch.dao;


import com.ch.model.Token;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * Created by apple on 2018/3/4.
 */
@Repository
@Mapper
public interface TokenDAO {

    @Select({"select * from token where id = #{id}"})
    Token selectTokenById(int id);

    @Select({"select * from token where ticket = #{ticket}"})
    Token selectByToken(String token);

    @Insert({"insert into token(user_id,ticket,expired,status)values(#{userId},#{ticket},#{expired},#{status})"})
    int insertToken(Token token);

}
