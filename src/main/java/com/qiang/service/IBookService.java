package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
public interface IBookService {

    /**
     * 分页模糊查询所有预约
     * @param num
     * @param now
     * @param bookid
     * @param plannum
     * @return
     */
    PageInfo<Book> findAll(Integer num,String now,String bookid,Integer plannum);


    /**
     * 保存预约单
     * @param book
     */
    void savebook(Book book);

    /**
     * 查询我的等待中的预约单
     * @param cs_id
     * @return
     */
    List<Book> findMyBook(String cs_id);

    /**
     * 查询我的历史预约单
     * @param cs_id
     * @return
     */
    List<Book> findMyoldBook(String cs_id);

    /**
     * 更新我的订单状态
     * @param book
     */
    void updateMyBook(Book book);

    /**
     *减少等待人数
     * @param abc
     */
    void cancelwaitnum(String abc);
}
