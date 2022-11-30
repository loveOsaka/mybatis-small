package org.hephaestus.mybatis.binding;

import org.hephaestus.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

public class MapperProxyFactory<T>{

    private Class<T> mapperInterface;
    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        MapperProxy proxy = new MapperProxy(sqlSession);
        return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, proxy);
    }
}
