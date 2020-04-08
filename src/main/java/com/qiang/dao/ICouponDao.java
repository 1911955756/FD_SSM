package com.qiang.dao;

import com.qiang.domain.Coupon;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-03-07
 */
@Repository
public interface ICouponDao {
    /**
     * 查询所有的优惠券
     * @return
     */
    List<Coupon> findAll(@Param("couname") String couname,@Param("coutype") String coutype,@Param("coustatus") String coustatus,@Param("couprice") Float couprice);
    /**
     * 查询所有上架的优惠券
     * @return
     */
    @Select("select * from coupon where status='上架' order by createtime desc")
    List<Coupon> findcouponAll();

    /**
     * 根据couponid查询优惠券信息
     * @param couponid
     * @return
     */
    @Select("select * from coupon where couponid=#{couponid}")
    Coupon findByid(String couponid);

    /**
     * 根据couponname查询优惠券信息
     * @param couponname
     * @return
     */
    @Select("select * from coupon where couponname like #{couponname} and status in('上架','下架')")
    List<Coupon> findCoupon(String couponname);

    /**
     * 保存优惠券
     * @param coupon
     */
    @Insert("insert into coupon(couponname,endtime,type,price,`condition`)values(#{couponname},#{endtime},#{type},#{price},#{condition})")
    void savecoupon(Coupon  coupon);

    /**
     * 根据couponid实现优惠券删除
     * @param coupon
     */
    @Update("update coupon set status=#{status} where couponid=#{couponid}")
    void updatecouponstatus(Coupon coupon);

    /**
     * 根据couponid实现优惠券上下架
     * @param coupon
     */
    @Update("update coupon set couponname=#{couponname},type=#{type},endtime=#{endtime},price=#{price},status=#{status},`condition`=#{condition} where couponid=#{couponid}")
    void updatecoupon(Coupon coupon);

    /**
     * 统计优惠券使用情况
     * @return
     */
    List<Map> countCoupon();
}
