package com.qiang.service;

import com.qiang.domain.Diets;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-05-09
 */
public interface IDietsService {
    List<Diets> findDietsBytitle(String title);
}
