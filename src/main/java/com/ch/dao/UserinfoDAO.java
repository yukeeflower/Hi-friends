package com.ch.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import com.ch.model.Userinfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by apple on 2018/2/7.
 */
@Repository
@Mapper
public interface UserinfoDAO {

//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;

//    public SqlSession getSqlSession(){
//        return sqlSessionFactory.openSession();
//    }
//
//    public Userinfo getUserinfo(int id){
//        return this.getSqlSession().selectOne("select * from Userinfo where id = #{id}",id);
//    }

    @Select({"select * from Userinfo where id = #{id}"})
    public Userinfo getUserinfo(int id);
}
