package org.hephaestus.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import org.hephaestus.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {

    private Map<Class<?>, MapperProxyFactory<?>> registry = new HashMap<>();

    public void addMappers(String path){
        Set<Class<?>> classTypes = ClassScanner.scanPackage(path);
        for(Class<?> type : classTypes){
            addMapper(type);
        }
    }

    public <T> void addMapper(Class<T> type){
        if(type.isInterface()){
            if(hasMapper(type)){
                throw new RuntimeException("");
            }else{
                registry.put(type, new MapperProxyFactory<>(type));
            }
        }
    }

    private <T>boolean hasMapper(Class<T> classType){
        return registry.containsKey(classType);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
       final MapperProxyFactory<T> factory = (MapperProxyFactory<T>) registry.get(type);
       if(factory == null){
           throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
       }
       try {
           return factory.newInstance(sqlSession);
       }catch (Exception e){
           throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
       }
    }
}
