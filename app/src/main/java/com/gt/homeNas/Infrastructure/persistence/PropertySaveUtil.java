package com.gt.homeNas.Infrastructure.persistence;

import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class PropertySaveUtil {

    private static Properties properties= new Properties();

    /*properties文件名*/
    private static final String PROPERTIES_FILE_NAME="/user.properties";


    /**
     * 初始化properties，即载入数据
     */
    private static void initProperties(){
        try (InputStream ips = PropertiesUtil.class.getResourceAsStream(PROPERTIES_FILE_NAME)) {
            properties.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**将数据载入properties，并返回"lastid"的值
     * @return
     */
    public static String getKey(String key){
        if(properties.isEmpty()) {
            initProperties();
        }
        return String.valueOf(properties.getProperty(key));
    }

    public static boolean saveKey(String key, String val){
        if(properties.isEmpty())
            initProperties();
        //修改值
        properties.setProperty(key, val);
        //保存文件
        try {
            URL fileUrl = PropertiesUtil.class.getResource(PROPERTIES_FILE_NAME);//得到文件路径
            FileOutputStream fos = new FileOutputStream(new File(fileUrl.toURI()));
            properties.store(fos, "the primary key of article table");
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
