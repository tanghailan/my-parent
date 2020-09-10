package com.kanavi.test.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author hailan
 * @version 1.0
 * @className UnsafeInstance
 * @description TODO
 * @date 2020-09-10 18:49
 */
public class UnsafeInstance {
    public static Unsafe reflectGetUnsafe(){
        Field field = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
