package org.hephaestus.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.hephaestus.mybatis.binding.MapperRegistry;
import org.hephaestus.mybatis.session.SqlSession;
import org.hephaestus.mybatis.session.SqlSessionFactory;
import org.hephaestus.mybatis.session.defaults.DefaultSqlSessionFactory;
import org.hephaestus.mybatis.test.dao.IUserDao;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
@Slf4j
public class HephaestusApplicationTest {

    @Test
    public void test_MapperProxyFactory() {
        // 1. 注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("org.hephaestus.mybatis.test.dao");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

//         3. 获取映射器对象
        IUserDao userDao = registry.getMapper(IUserDao.class, sqlSession);

        // 4. 测试验证
        String res = userDao.queryUserName("10001");
        log.info("测试结果：{}", res);
    }

}
