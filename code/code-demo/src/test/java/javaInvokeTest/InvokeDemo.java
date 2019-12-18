package javaInvokeTest;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InvokeDemo {


    public static void main(String[] args) {

// test
    }


    public String getData(Map<String, Object> params) {
        System.out.println("参数为：" + JSON.toJSONString(params));
        return UUID.randomUUID().toString();
    }


    @Test
    public void test1() {
        try {
            Class<?> aClass = Class.forName("javaInvokeTest.InvokeDemo");

            Method[] methods = aClass.getMethods();

            System.out.println(JSON.toJSONString(methods, true));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        // 1
        Class<?> aClass = null;
        try {
            aClass = Class.forName("javaInvokeTest.InvokeDemo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2
        Object newInstance = null;
        try {
            newInstance = aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 3
        Method method = null;
        try {
            method = aClass.getMethod("getData", Map.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Object invoke = null;
        try {
            Map<String, Object> map = Maps.newHashMap();
            map.put("name", "zhao");
            map.put("age", 18);
            map.put("money", new BigDecimal("11.11"));
            invoke = method.invoke(newInstance, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(JSON.toJSONString(invoke));
    }


    @Test
    public void test3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = this.getClass().getDeclaredMethod("getData", Map.class);

        HashMap<Object, Object> map = Maps.newHashMap();

        Object invoke = method.invoke(this, map);

        System.out.println(invoke);
    }
}
