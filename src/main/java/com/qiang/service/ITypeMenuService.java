package com.qiang.service;

import com.qiang.domain.TypeMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-22
 */
public interface ITypeMenuService {

        /**
         * 保存菜单类型
         * @param tymenu
         */
        void saveTypeMenu(TypeMenu tymenu);

    /**
     * 删除菜单类型
     * @param tymenu
     */
    void deleteTypeMenu(TypeMenu tymenu);

    /**
     * 查询typeid
     * @param typeid
     * @return
     */
    List<TypeMenu> findtypeid(String typeid,String menuid);

    }

