package com.example.yorkyan.mylibrary;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {
    public static String getPhoneNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager.getLine1Number() == null) {
            return "";
        }

        return telephonyManager.getLine1Number();
    }

    public static String getDeviceID(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId();
    }

    //    移动：134、135、136、137、138、139、150、151、157（TD）、158、159、187、188
    //
    //    联通：130、131、132、152、155、156、185、186
    //
    //    电信：133、153、180、189、（1349卫星）
    public static boolean isMobileNumber(String mobile) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }
}
