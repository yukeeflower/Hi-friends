package com.ch.dao;

import com.ch.model.Tips;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 2018/2/28.
 */
@Mapper
@Repository
public interface TipsDAO {

    @Select({"select * from tips where id = #{id}"})
    Tips selectTipById(int id);

}
