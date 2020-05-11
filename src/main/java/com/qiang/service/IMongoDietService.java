package com.qiang.service;
import com.qiang.domain.Mongodiet;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-05-08
 */
public interface IMongoDietService {
    /**
     *分页查询所有饮食信息
     * @param start
     * @param size
     * @return
     */
    List<Mongodiet> findAll(String name,Integer start,Integer size);

    /**
     * 统计总信息数量
     * @return
     */
    Long counttotal(String name);
}
