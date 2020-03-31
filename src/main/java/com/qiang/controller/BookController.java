package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Book;
import com.qiang.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = false,defaultValue ="1") Integer num,
                                @RequestParam(required = false,defaultValue ="yes") String now,
                                @RequestParam(required = false) String bookid,
                                @RequestParam(required = false) Integer plannum){
        ModelMap modelMap=new ModelMap();
        //调用service的方法
        PageInfo<Book> list = bookService.findAll(num,now,bookid,plannum);
        modelMap.addAttribute("booklist",list);
        modelMap.addAttribute("now",now);
        modelMap.addAttribute("bookid",bookid);
        modelMap.addAttribute("plannum",plannum);
        ModelAndView mv=new ModelAndView("mvlist",modelMap);
        mv.setViewName("booklist");
        return mv;
    }
    @RequestMapping("/findAll2")
    public @ResponseBody PageInfo findAll2(@RequestParam(required = false,defaultValue ="1") Integer num,
                                           @RequestParam(required = false,defaultValue ="yes") String now,
                                           @RequestParam(required = false) String bookid,
                                           @RequestParam(required = false) Integer plannum){
        //调用service的方法
        PageInfo<Book> list = bookService.findAll(num,now,bookid,plannum);
        return list;
    }
    @RequestMapping("/savebook")
    public @ResponseBody void savebook(Book book){
        bookService.savebook(book);
    }

    @RequestMapping("/findMyBook")
    public @ResponseBody List findMyBook(String cs_id,Integer type){
        List<Book> myBook = null;
        if(type==0){
        myBook = bookService.findMyBook(cs_id);
        }
        else if(type==1){
            myBook = bookService.findMyoldBook(cs_id);
        }
        return  myBook;
    }
    @RequestMapping("/cancelMyBook")
    public @ResponseBody void cancelMyBook(Book book){
        bookService.cancelwaitnum(book.getBookid());
        bookService.updateMyBook(book);
    }
}
