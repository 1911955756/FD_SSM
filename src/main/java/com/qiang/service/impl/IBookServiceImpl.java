package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.IBookDao;
import com.qiang.domain.Book;
import com.qiang.service.IBookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Service("bookService")
public class IBookServiceImpl implements IBookService {
    @Autowired
    private IBookDao bookDao;
    @Override
    public PageInfo<Book> findAll(Integer num,String now,String bookid,Integer plannum) {
        PageHelper.startPage(num,10);
        List<Book> all = bookDao.findAll(now,bookid,plannum);
        PageInfo<Book> bookPageInfo = new PageInfo<>(all);
        return bookPageInfo;
    }

    @Override
    public void savebook(Book book) {
        bookDao.savebook(book);
    }

    @Override
    public List<Book> findMyBook(String cs_id) {
        return bookDao.findMyBook(cs_id);
    }

    @Override
    public List<Book> findMyoldBook(String cs_id) {
        return bookDao.findMyoldBook(cs_id);
    }

    @Override
    public void updateMyBook(Book book) {
        bookDao.updateMyBook(book);
    }

    @Override
    public void cancelwaitnum(String abc) {
        bookDao.cancelwaitnum(abc);
    }
}
