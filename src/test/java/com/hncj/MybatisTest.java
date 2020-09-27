package com.hncj;

import com.hncj.bean.book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    @Test
    /*执行查询的*/
    public void testMyBatis() throws IOException {
        //1、根据应用程序根据mybatis的全局配置文件生成SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        //2、根据SqlSessionFactory对象的openSession方法得到sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3、通过sqlSession调用SQL映射文件的sql语句执行，并接收返回结果
        Object o = sqlSession.selectOne("org.mybatis.example.BlogMapper.selectid", 1);
        System.out.println(o);

        //4、关闭SqlSession对象
        sqlSession.close();
    }

    @Test
    /*执行添加操作的
    *  * 如果用mybatis对数据表增删改操作的时候，一定要提交事务。提交事务有两种方式，
     *   第一种：自动提交: sqlSessionFactory.openSession(true);
     *   第二种：手动提交:sqlSession.commit();
    * */
    public void testInsert() throws IOException {
        //1、根据应用程序根据mybatis的全局配置文件生成SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        //2、根据SqlSessionFactory对象的openSession方法得到sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//加true是为了自动提交事务，这是必须要加的

        book Book=new book();
        Book.setBookname("西游记");
        Book.setPrice(200);
        Book.setAuthor("吴承恩");

        //3、通过sqlSession调用SQL映射文件的sql语句执行，并接收返回结果
        sqlSession.insert("org.mybatis.example.BlogMapper.insert", Book);

        //如果上面没有设置自动提交事务，那么这里在调用完sql语句以后就得手动提交事务
        //sqlSession.commit();

        //4、关闭SqlSession对象
        sqlSession.close();
    }

    @Test
    /*执行删除操作的
     *  * 如果用mybatis对数据表增删改操作的时候，一定要提交事务。提交事务有两种方式，
     *   第一种：自动提交: sqlSessionFactory.openSession(true);
     *   第二种：手动提交:sqlSession.commit();
     * */
    public void testDelete() throws IOException {
        //1、根据应用程序根据mybatis的全局配置文件生成SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        //2、根据SqlSessionFactory对象的openSession方法得到sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//加true是为了自动提交事务，这是必须要加的

        //3、通过sqlSession调用SQL映射文件的sql语句执行，并接收返回结果
        sqlSession.delete("org.mybatis.example.BlogMapper.delete", 3);

        //如果上面没有设置自动提交事务，那么这里在调用完sql语句以后就得手动提交事务
        //sqlSession.commit();

        //4、关闭SqlSession对象
        sqlSession.close();
    }

    @Test
    /*执行修改操作的
     *  * 如果用mybatis对数据表增删改操作的时候，一定要提交事务。提交事务有两种方式，
     *   第一种：自动提交: sqlSessionFactory.openSession(true);
     *   第二种：手动提交:sqlSession.commit();
     * */
    public void testUpdate() throws IOException {
        //1、根据应用程序根据mybatis的全局配置文件生成SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        //2、根据SqlSessionFactory对象的openSession方法得到sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//加true是为了自动提交事务，这是必须要加的

          book Book=new book();
          Book.setBid(1);
          Book.setBookname("哪吒脑海");
        //3、通过sqlSession调用SQL映射文件的sql语句执行，并接收返回结果
        sqlSession.update("org.mybatis.example.BlogMapper.update", Book);

        //如果上面没有设置自动提交事务，那么这里在调用完sql语句以后就得手动提交事务
        //sqlSession.commit();

        //4、关闭SqlSession对象
        sqlSession.close();
    }
}
