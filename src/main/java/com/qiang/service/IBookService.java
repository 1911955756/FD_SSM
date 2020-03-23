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

    PageInfo<Book> findAll(Integer num,String now,String bookid,Integer plannum);


    /**
     * 保存预约单
     * @param book
     */
    @Insert("insert into book(cs_id,plan_num)values(#{cs_id},#{plan_num})")
    void savebook(Book book);

    /**
     * 查询我的等待中的预约单
     * @param cs_id
     * @return
     */
    @Select("select * from book where cs_id=#{cs_id} and status='等待中'")
    List<Book> findMyBook(String cs_id);

    /**
     * 查询我的历史预约单
     * @param cs_id
     * @return
     */
    @Select("select * from book where cs_id=#{cs_id} and status！='等待中' order by createtime desc")
    List<Book> findMyoldBook(String cs_id);

    /**
     * 更新我的订单状态
     * @param book
     */
    @Update("update book set status=#{status} where bookid=#{bookid}")
    void updateMyBook(Book book);

    void cancelwaitnum(String abc);
}
