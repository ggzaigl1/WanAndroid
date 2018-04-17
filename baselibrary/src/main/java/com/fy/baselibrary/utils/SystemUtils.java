package com.fy.baselibrary.utils;

/**
 * Created by 初夏小溪 on 2018/4/17 0017.
 */

public class SystemUtils {

    /**
     *  退出程序
     */
    public static void ExitSystem(){
        android.os.Process.killProcess(android.os.Process.myPid());  //获取PID
        System.exit(0);
    }
}
