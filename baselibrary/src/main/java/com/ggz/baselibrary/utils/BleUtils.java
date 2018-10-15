package com.ggz.baselibrary.utils;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 低功耗蓝牙工具类
 * Created by fangs on 2017/4/17.
 */
public class BleUtils {

    /**
     * 获取本机的 蓝牙adapter
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static BluetoothAdapter getBleAdapter(Context context){
        final BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();

        return bluetoothAdapter;
    }

    /**
     * 判断是否支持蓝牙4.0
     * @param context
     * @return
     */
    public static boolean isHaveBluetooth(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    /**
     * 判断蓝牙是否打开
     * @param context
     * @return true表示已经开启
     */
    public static boolean isBluetoothOpen(Context context) {

        BluetoothAdapter bluetoothAdapter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bluetoothAdapter = getBleAdapter(context);
        }

        return !(bluetoothAdapter == null || !bluetoothAdapter.isEnabled());
    }

    /**
     * 强制开启当前 Android 设备的 Bluetooth
     * @param context
     * @return true：强制打开 Bluetooth　成功　false：强制打开 Bluetooth 失败
     */
    public static boolean turnOnBluetooth(Context context) {

        BluetoothAdapter bluetoothAdapter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bluetoothAdapter = getBleAdapter(context);
        }

        return null != bluetoothAdapter && bluetoothAdapter.enable();

    }

    /**
     * 强制关闭当前 Android 设备的 Bluetooth
     * @param context
     * @return  true：强制关闭 Bluetooth　成功　false：强制关闭 Bluetooth 失败
     */
    public static boolean turnOffBluetooth(Context context) {

        BluetoothAdapter bluetoothAdapter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bluetoothAdapter = getBleAdapter(context);
        }
        return null != bluetoothAdapter && bluetoothAdapter.disable();

    }

    /**
     * 判断蓝牙是否打开，如果打开就重启蓝牙
     * @param context
     */
    @SuppressLint("CheckResult")
    public static void reStartBluetooth(final Context context) {
        //先关闭手机蓝牙
        turnOffBluetooth(context);

        //延迟1S 打开手机蓝牙
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> turnOnBluetooth(context));
    }
}
