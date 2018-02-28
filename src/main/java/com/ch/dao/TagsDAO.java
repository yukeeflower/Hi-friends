package com.ch.dao;

import com.ch.model.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 2018/2/28.
 */
@Mapper
@Repository
public interface TagsDAO {

    @Select({"select * from Tags where id = #{id}"})
    Tags getTagById(int id);
}
