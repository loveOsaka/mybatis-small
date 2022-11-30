package org.hephaestus.mybatis.session.defaults;

import org.hephaestus.mybatis.binding.MapperRegistry;
import org.hephaestus.mybatis.session.SqlSession;
import org.hephaestus.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private MapperRegistry registry;

    public DefaultSqlSessionFactory(MapperRegistry registry) {
        this.registry = registry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(registry);
    }
}
