package com.jiqu.data.network;

/**
 * Created by CJJ on 2017/3/6.
 *
 */

public class Url {

     private static String DEFAULT_URL;
     private static String DEFAULT_DEBUG_URL;
     private static String DEFAULT_RELEASE_URL;
     private static String DEFAULT_DEVELOPER_URL;

   public String defaultUrl(){
        return DEFAULT_URL;
    }

    public static void setDebug() {
        DEFAULT_URL = DEFAULT_DEBUG_URL;
    }

    public void setDeveloper() {
        DEFAULT_URL = DEFAULT_DEVELOPER_URL;
    }

    public void setRelease() {
        DEFAULT_URL = DEFAULT_RELEASE_URL;
    }
}
