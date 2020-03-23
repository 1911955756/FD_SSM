package com.qiang.dao;

import com.qiang.domain.Table1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Repository
public interface ITableDao {
    /**
     * 查询所有餐桌
     * @return
     */
    List<Table1> findAll(@Param("taid") String taid,@Param("penum") Integer penum,@Param("tasta") String tasta);

    @Update("update table1 set status=#{status} where tableid=#{tableid}")
    void updatetablestatus(Table1 table1);

    /**
     * 保存餐桌
     * @param table1
     */
    @Insert("insert into table1(tableid,people_num)values(#{tableid},#{people_num})")
    void saveTable(Table1 table1);

    List<Table1> findTpeoplenum();
}