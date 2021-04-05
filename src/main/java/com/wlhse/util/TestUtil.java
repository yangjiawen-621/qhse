//package com.wlhse.util;
//
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//@Component
//public class TestUtil<T>{
//    public String test(String className,String methodName,T t,Class parms) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Class<?> aClass = Class.forName(className);
//        Object o = aClass.newInstance();
//        Method method = aClass.getMethod(methodName, parms);
//        Object invoke = method.invoke(o, t);
//        System.out.println(invoke);
//        return null;
//    }
//}
