package com.luoyun.course.jvm01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Method;

/**
 * XlassLoader
 *
 * @author luoyun
 * @data 2021/9/21
 */
public class XlassLoader extends ClassLoader{

    private static Logger logger = LoggerFactory.getLogger(XlassLoader.class);

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String separator = "/";
        String suffix = ".xlass";
        String filePath = separator + name + suffix;
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getResourceAsStream(filePath);
            if (inputStream == null){
                logger.error("findClass filePath:{} read file fail,case inputStream is null",filePath);
                return null;
            }
            byte[] byteArray = new byte[inputStream.available()];
            inputStream.read(byteArray);

            for(int i=0;i<byteArray.length;i++){
                byte byteVal = byteArray[i];
                byteArray[i] = (byte)(255 - byteVal);
            }

            return defineClass(name,byteArray,0,byteArray.length);

        }catch (Exception e){
            logger.error("findClass filePath:{} read file error",filePath,e);
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("findClass filePath:{} close inputStream error",filePath,e);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        String className = "Hello";
        String methodName = "hello";
        try {
            Class<?> clz = new XlassLoader().findClass(className);
            if (clz == null){
                return ;
            }
            Method method = clz.getDeclaredMethod(methodName);
            method.invoke(clz.newInstance());
        }catch (Exception e){
            logger.error("className:{} invoke method error",className,e);
        }

    }
}
