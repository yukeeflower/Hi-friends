package com.ch.dao;

import com.ch.model.Remark;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by apple on 2018/2/28.
 */
@Mapper
@Repository
public interface RemarkDAO {

    @Select({"select * from remark where id = #{id}"})
    Remark selectRemarkById(int id);

    @Select({"select * from remark where entity_type = #{entityType} and entity_id = #{entityId} order by create_time desc"})
    List<Remark> selectRemarkByTypeId(@Param("entityType") int entityType, @Param("entityId")int entityId);

    @Insert({"insert into remark(user_id,content,entity_type,entity_id,create_time)values(#{userId},#{content},#{entityType},#{entityId},#{createTime})"})
    int insertRemark(Remark remark);

}
