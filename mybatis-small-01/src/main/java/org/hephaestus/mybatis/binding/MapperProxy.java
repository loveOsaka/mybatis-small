package org.hephaestus.mybatis.binding;

import lombok.extern.slf4j.Slf4j;
import org.hephaestus.mybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(proxy, args);
        }else{
            return "测试类被代理了：{}-{}" + method.getDeclaringClass() + method.getName();
        }
    }
}
