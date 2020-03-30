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


    /**
     * 更新餐桌信息
     * @param table1
     */
    void updatetablestatus(Table1 table1);

    /**
     * 查询所有餐桌
     * @return
     */
    List<Table1> findtable();

    /**
     * 保存餐桌
     * @param table1
     */
    void saveTable(Table1 table1);

    /**
     * 查询餐桌人数
     * @return
     */
    List<Table1> findTpeoplenum();
}
