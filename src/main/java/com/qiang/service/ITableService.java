package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Table1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
public interface ITableService {
    /**
     * 查询所有餐桌
     * @return
     */
    PageInfo<Table1> findAll(Integer num,String taid,Integer penum,String tasta);

    @Update("update table1 set status=#{status} where tableid=#{tableid}")
    void updatetablestatus(Table1 table1);
    /**
     * 查询所有餐桌
     * @return
     */
    @Select("select * from table1")
    List<Table1> findtable();

    /**
     * 保存餐桌
     * @param table1
     */
    @Insert("insert into table1(tableid,people_num)values(#{tableid},#{people_num})")
    void saveTable(Table1 table1);

    List<Table1> findTpeoplenum();
}
