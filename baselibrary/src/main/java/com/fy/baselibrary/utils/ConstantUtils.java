package com.fy.baselibrary.utils;

/**
 * 常量
 * Created by fangs on 2017/5/8.
 */
public class ConstantUtils {

    /**
     * 动态服务器地址
     */
    public static String custom_Url = "";

    /**
     * 区分不同用户的缓存 key
     */
    public static String userId = "userid";

    /**
     * 操作令牌
     */
    public static String token = "";

    /**
     * 角色
     */
    public static String role = "";

    /**
     * student实体类中id
     */
    public static int studentID = 0;

    /**
     * 当前个人信息图片上传 key
     */
    public static String selectvisitimager = "selectvisitimager";

    /**
     * 学生实体类 缓存 key
     */
    public static final String student = "stuInfo";

    /**
     * APP 当前模式 （日间/夜间）
     */
    public static final String appMode = "appModeSwitch";

    /**
     * 用户是否 第一次打开APP
     */
    public static String isfirstOpenApp = "";

    /**
     * 普通 状态栏
     */
    public static final int OrdinaryStatus = 1;
    /**
     * 全屏透明 状态栏
     */
    public static final int ImageTransparent = 2;
    /**
     * 全屏半透明 状态栏
     */
    public static final int ImageTranslucent = 3;

    /**
     * ToolBar+TabLayout可伸缩 状态栏
     */
    public static final int TelescopicStatus = 4;
    /**
     * DrawerLayout+ToolBar+TabLayout 可伸缩 状态栏
     */
    public static final int DrawerToolbarTabLayout = 5;
    /**
     * CollapsingToolbarLayout状态栏(可折叠图片) 状态栏
     */
    public static final int CollapsingToolbar = 6;
    /**
     * CollapsingToolbar折叠时statusBar显示和隐藏 状态栏
     */
    public static final int CollapsingToolbarStatus = 7;

    /**
     * DrawerLayout+ToolBar 状态栏
     */
    public static final int DrawerToolbar = 8;

    /**
     * 年级编码
     */
    public static String ncode = "";

    /**
     * 学生体质信息 缓存 key
     */
    public static final String totalHealthInfoBean = "";

    /**
     * 学生头像
     */
    public static String head_portrait = "";

    //练习项目
    public static final String SPORTS_METHODTOAPP = "sports_methodtoapp";
    //获取某日打卡记录
    public static final String PUNCH_CLOCKTOAPP = "punch_clocktoapp";
    //获取获取某月打卡记录日期
    public static final String PUNCHC_LOCKBYMONTH = "punchc_lockbymonth";


}
