package com.ch.dao;

import com.ch.model.Remark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 2018/2/28.
 */
@Mapper
@Repository
public interface RemarkDAO {

    @Select({"select * from remark where id = #{id}"})
    Remark getRemarkById(int id);
}
