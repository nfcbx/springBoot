package ioc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ZSX on 2018/11/6.
 *
 * @author ZSX
 */
public class Test1 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {


        Class<?> integerClass = Class.forName("java.lang.Integer");

        Method[] methods = integerClass.getMethods();
        for (Method method : methods) {
//            System.out.println(method);
        }

        Method parseInt = integerClass.getMethod("parseInt", String.class);

        Object invoke = parseInt.invoke(integerClass,"123");


        System.out.println(invoke);


//   ************************************************************

        Object object = Class.forName(String.class.getName()).newInstance();


        // 获取方法
        Method m = object.getClass().getMethod("valueOf", int.class);

        Object invoke1 = m.invoke(object, 111);

        System.out.println(object);
        System.out.println(invoke1);

//        newInstance



    }

}
