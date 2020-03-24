package com.qiang.test;



import com.qiang.dao.*;
import com.qiang.domain.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


/**
 * @author Mr.锵
 * date 2020-02-16
 */
public class TestMyBatis {
    private InputStream in;
    private SqlSession session;
    private ITypeDao typeDao;
    private IUserDao userDao;
    private IMenuDao menuDao;
    private ICustomerDao customerDao;
    private IOrderDetailDao orderDetailDao;
    private ITableDao tableDao;
    private IBookDao bookDao;
    private IEvaluationDao evaluationDao;
    private ICouponDao couponDao;
    private ICsCouponDao csCouponDao;
    private IOrderDao orderDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产session对象
        session = factory.openSession(true);
        //4.使用sqlSession创建Dao接口的代理对象
        evaluationDao=session.getMapper(IEvaluationDao.class);
        couponDao=session.getMapper(ICouponDao.class);
        csCouponDao=session.getMapper(ICsCouponDao.class);
        typeDao = session.getMapper(ITypeDao.class);
        userDao=session.getMapper(IUserDao.class);
        menuDao=session.getMapper(IMenuDao.class);
        customerDao=session.getMapper(ICustomerDao.class);
        orderDetailDao=session.getMapper(IOrderDetailDao.class);
        orderDao=session.getMapper(IOrderDao.class);
        tableDao=session.getMapper(ITableDao.class);
        bookDao=session.getMapper(IBookDao.class);
        System.out.println("初始化成功");
    }

    @After//用于在测试方法执行之后执行
    public void destroy() throws Exception {
        //6.事务提交
        //session.commit();
        //7.释放资源
        session.close();
        in.close();
        System.out.println("资源释放成功");
    }
    @Test
    public void testfindAll(){
        List<OrderDetail> csso = orderDetailDao.findMyorderdetail("OR202002291015");
        for(OrderDetail orderDetail:csso){
            System.out.println(orderDetail);
            System.out.println(orderDetail.getOrder1());
            System.out.println(orderDetail.getMenu());
            System.out.println("-------------");
        }
    }
    @Test
    public void testfindTpeoplenum(){
        List<Table1> tpeoplenum = tableDao.findTpeoplenum();
        for (Table1 table1:tpeoplenum){
            System.out.println(table1);
            System.out.println(table1.getBooks());
            System.out.println("===========================");
        }
    }

    @Test
    public  void testcancelwaitnum(){
        bookDao.cancelwaitnum("B202003031003");
    }

    @Test
    public void testevaluation(){
        List<Evaluation> all = evaluationDao.findAll();
        for (Evaluation evaluation:all){
            System.out.println(evaluation);
            System.out.println(evaluation.getCustomer());
        }
    }

    @Test
    public void testcoupon()throws Exception{
        String createDate = "20200701121212";
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp t1 = Timestamp.valueOf(sdf2.format(sdf.parse(createDate)));
        Coupon coupon=new Coupon();
        coupon.setCouponname("9折");
        coupon.setEndtime(t1);
        coupon.setPrice(0.9f);
        coupon.setType("折扣券");
        couponDao.savecoupon(coupon);
        List<Coupon> all = couponDao.findcouponAll();
        for (Coupon coupon1:all){
            System.out.println(coupon1);
        }
    }
    @Test
    public void testCsCoupon(){
        List<CsCoupon> mine = csCouponDao.findMine("CS202002291006");
        for (CsCoupon csCoupon:mine){
            System.out.println(csCoupon);
            System.out.println(csCoupon.getCoupon());
            System.out.println("================================");
        }
    }
    @Test
    public void testFindMenu(){
        int i=3;
        Integer a=(i-1)*5;
        List<Menu> all = menuDao.findAll(null,null);
        for (Menu menu:all){
            System.out.println(menu);
        }
    }

    @Test
    public void testcountpeoplenum(){
        List<Map> countpeoplenum = orderDao.countpeoplenum();
        for(Map order1:countpeoplenum){
            System.out.println(order1);
        }
    }
    @Test
    public void testcountnewfood(){
        List<Map> countnewfood = menuDao.countnewfood();
        for(Map order1:countnewfood){
            System.out.println(order1);
        }
    }
    @Test
    public void testOrderfindAll() {
        List<Order1> all = orderDao.findAll("no",null,null,null);
        for (Order1 order1:all){
            System.out.println(order1);
        }
    }
}
