package com.ch.dao;

import com.ch.model.Favor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 2018/2/28.
 */
@Repository
@Mapper
public interface FavorDAO {

    @Select({"select * from Favor where id = #{id}"})
    Favor getFavorById(int id);
}
