package com.cmq.syk.util;

import java.io.IOException;

public class RuntimeUtil {

    public RuntimeUtil() {

    }


    public void browse(String url){
        String os = System.getProperty("os.name");
        String prefix = "cmd /c start ";
        if (os.startsWith("Mac")){
            prefix = "open ";
        }
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(prefix + url);
//                                  cmd /c start http://blog.csdn.net/jiezhi2013  //for windows
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
