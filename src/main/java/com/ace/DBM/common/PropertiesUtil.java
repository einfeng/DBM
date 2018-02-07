package com.ace.DBM.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	private static final String filePath = "./src/main/java/com/ace/DBM/config/DBConfig.properties";
	
    public static String GetValueByKey(String key) {
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));  
            pps.load(in);
            String value = pps.getProperty(key);
            //System.out.println(key + " = " + value);
            return value;
            
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
