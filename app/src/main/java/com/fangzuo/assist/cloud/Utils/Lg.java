package com.fangzuo.assist.cloud.Utils;

import android.util.Log;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.google.gson.Gson;

public class Lg {

    public static void e(String string){
        if (App.isDebug){
            if (string!=null){
                Log.e("TEST","\n"+string);
            }
        }
    }
    public static void e(String tag,String string){
        if (App.isDebug){
            if (string!=null){
                Log.e(tag,"\n"+string);
            }
        }
    }
    public static void e(String tag,Object string){
        if (App.isDebug){
            if (string!=null){
                Log.e(tag,"\n"+new Gson().toJson(string));
            }else{
                Log.e(tag,"\n"+"对象未空");
            }
        }
    }
}
