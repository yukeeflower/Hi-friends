package com.ch.dao;

import com.ch.model.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 2018/2/28.
 */
@Mapper
@Repository
public interface MessageDAO {

    @Select({"select * from message where id = #{id}"})
    Message getMessageById(int id);
}
