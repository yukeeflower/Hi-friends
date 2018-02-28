package com.ch.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.ch.model.Userinfo;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 2018/2/7.
 */
@Repository
@Mapper
public interface UserinfoDAO {

    @Select({"select * from Userinfo where id = #{id}"})
    Userinfo getUserinfoById(int id);


}