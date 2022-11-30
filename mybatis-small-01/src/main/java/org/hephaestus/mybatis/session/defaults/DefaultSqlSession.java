package org.hephaestus.mybatis.session.defaults;

import org.hephaestus.mybatis.binding.MapperRegistry;
import org.hephaestus.mybatis.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    private MapperRegistry registry;

    public DefaultSqlSession(MapperRegistry registry) {
        this.registry = registry;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T)("测试代理" + statement + "参数 :" + parameter) ;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return registry.getMapper(type, this);
    }
}
