package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.IMenuDao;
import com.qiang.dao.ITypeDao;
import com.qiang.dao.ITypeMenuDao;
import com.qiang.domain.Book;
import com.qiang.domain.Menu;
import com.qiang.domain.TypeMenu;
import com.qiang.service.IMenuService;
import com.qiang.utils.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Service("menuService")
public class IMenuServiceImpl implements IMenuService {
    @Autowired
    private IMenuDao menuDao;
    @Autowired
    private ITypeDao typeDao;
    @Autowired
    private ITypeMenuDao typeMenuDao;
    @Override
    public PageInfo<Menu> findAll(Integer num,String mname,String msta) {
        PageHelper.startPage(num,5);
        List<Menu> all = menuDao.findAll(mname,msta);
        PageInfo<Menu> pageInfo=new PageInfo<Menu>(all);
        return pageInfo;
    }

    @Override
    public void deleteMenu(String menuid) {
        menuDao.deleteMenu(menuid);
    }

    @Override
    public Menu findByMenuid(String menuid) {
        return menuDao.findByMenuid(menuid);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuDao.updateMenu(menu);
    }

    @Override
    public void saveMenu(Menu menu) {
        menuDao.saveMenu(menu);
    }

    @Override
    public List<Menu> findMenuByName(String menuname) {
        return menuDao.findMenuByName(menuname);
    }

    @Override
    public List<Map> countsellnum() {
        return menuDao.countsellnum();
    }

    @Override
    public List<Map> countnewfood() {
        return menuDao.countnewfood();
    }

    @Override
    public String readExcelFile(MultipartFile file) {
        //创建处理EXCEL的类
        ReadExcel readExcel=new ReadExcel();
        //解析excel，获取上传的事件单
        List<Menu> menuList = null;
        int insertResult = 0;
        String insertMsg = "";
        try {
            menuList = readExcel.getExcelInfo(file);
            //将excel中的数据转换到list里面
            //数据库插入
            for(Menu menu:menuList) {
                String menuid = menuDao.findIdByMenuname(menu.getMenuname());
                if(menuid==null){
                menuDao.saveMenu(menu);
                    if(menu.getType1().getName()!="no"){
                        String idByname =typeDao.findIdByname(menu.getType1().getName());
                        if(idByname==null){
                            typeDao.saveType(menu.getType1().getName());
                            idByname = typeDao.findIdByname(menu.getType1().getName());
                        }
                        String idByMenuname = menuDao.findIdByMenuname(menu.getMenuname());
                        TypeMenu typeMenu = new TypeMenu();
                        typeMenu.setMenuid(idByMenuname);
                        typeMenu.setTypeid(idByname);
                        typeMenuDao.saveTypeMenu(typeMenu);
                    }
                }
                else{
                    menu.setMenuid(menuid);
                    menu.setStatus("上架");
                    menuDao.updateMenu(menu);
                }
                insertResult=insertResult+1;
            }
            if(insertResult ==0) {
                insertMsg = "All insert false";
            }else if(insertResult == menuList.size()){
                insertMsg = "All insert success";
            }else {
                insertMsg = "Part insert success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("接受excel表格中的数据失败！！！");
        }
        return insertMsg;

    }
}
    
