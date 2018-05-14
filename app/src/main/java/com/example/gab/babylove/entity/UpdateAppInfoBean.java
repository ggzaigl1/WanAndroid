package com.example.gab.babylove.entity;

import java.io.Serializable;

/**
 * Created by 初夏小溪 on 2018/5/14 0014.
 */

public class UpdateAppInfoBean  implements Serializable{

    // app名字
    public String appname;
    //服务器版本
    public int serverVersion;
    //服务器标志
    public String serverFlag;
    //强制升级
    public String lastForce;
    //app最新版本地址
    public String updateurl;
    //升级信息
    public String upgradeinfo;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public int getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(int serverVersion) {
        this.serverVersion = serverVersion;
    }

    public String getServerFlag() {
        return serverFlag;
    }

    public void setServerFlag(String serverFlag) {
        this.serverFlag = serverFlag;
    }

    public String getLastForce() {
        return lastForce;
    }

    public void setLastForce(String lastForce) {
        this.lastForce = lastForce;
    }

    public String getUpdateurl() {
        return updateurl;
    }

    public void setUpdateurl(String updateurl) {
        this.updateurl = updateurl;
    }

    public String getUpgradeinfo() {
        return upgradeinfo;
    }

    public void setUpgradeinfo(String upgradeinfo) {
        this.upgradeinfo = upgradeinfo;
    }
}
