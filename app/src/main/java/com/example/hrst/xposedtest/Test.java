package com.example.hrst.xposedtest;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.lang.reflect.Field;
import android.text.SpannableStringBuilder;


import de.robv.android.xposed.IXposedHookLoadPackage;
/**
 * Created by hrst on 2017/6/7.
 */

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XposedHelpers;

public class Test implements IXposedHookLoadPackage {

    //    public static String patternToString(List<LockPatternView.Cell> pattern) {
//             if (pattern == null) {
//                    return "";
//                   }
//            final int patternSize = pattern.size();
//
//           byte[] res = new byte[patternSize];
//             for (int i = 0; i < patternSize; i++) {
//                      LockPatternView.Cell cell = pattern.get(i);
//                     res[i] = (byte) (cell.getRow() * 3 + cell.getColumn());
//                 }
//              return new String(res);
//          }
    private static final String TAG = "DEX_DUMP";
//    private void hook_method(String className, ClassLoader classLoader, String methodName,
//                             Object... parameterTypesAndCallback) {
//        try {
//            XposedHelpers.findAndHookMethod(className, classLoader, methodName, parameterTypesAndCallback);
//        } catch (Exception e) {
//            XposedBridge.log(e);
//        }
//    }

//    private void hook_methods(String className, String methodName, XC_MethodHook xmh) {
//        try {
//            Class<?> clazz = Class.forName(className);
//            for (Method method : clazz.getDeclaredMethods())
//                if (method.getName().equals(methodName)
//                        && !Modifier.isAbstract(method.getModifiers())
//                        && Modifier.isPublic(method.getModifiers())) {
//                    XposedBridge.hookMethod(method, xmh);
//                }
//        } catch (Exception e) {
//            XposedBridge.log(e);
//        }
//    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        List<String> hooklist = Config.getConfig();

        if(!Config.contains(hooklist, loadPackageParam.packageName))
            return;

        XposedBridge.log("对" + loadPackageParam.packageName + "进行处理");
        Log.e(TAG, "开始处理: " + loadPackageParam.packageName);
        String TMP = loadPackageParam.packageName;
        Log.e(TAG, "handleLoadPackage: " );
        try{
            System.load("/data/data/com.example.hrst.xposedtest/lib/libhook.so");
        }catch (Exception e) {
            Log.e(TAG, "加载动态库失败：" + e.getMessage());
        }
        Log.e(TAG, "加载动态库成功");
        XposedHelpers.findAndHookMethod("android.app.Application", loadPackageParam.classLoader, "attach", Context.class, new XC_MethodHook() {
            private Context context;
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Log.e(TAG, "beforeHookedMethod");
                Dumpper.dump();
                Log.e(TAG, "beforeHookedMethod2");
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });

    }
}


            //   Class clasz = loadPackageParam.classLoader.loadClass("com.taobao.wireless.security.adapter.JNICLibrary");
            //     Class clazz11 = XposedHelpers.findClass("com.isentech.cq2102driver.a",loadPackageParam.classLoader);
            //  Class clasz1 = loadPackageParam.classLoader.loadClass("com.android.keyguard.KeyguardPatternView");
//com/android/server/pm/UserManagerService
            //  Log.e("kwing", "replaceHookedMethod112221: "+clasz);

//        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                // place your hooks here, it should work with lpparam.classLoader
//                XposedBridge.log("kwing attach is ok");
//                Class<?> QueryResult = null;
//                Context context = (Context) param.args[0];
//                //   hookMultiClound(context, context.getClassLoader());
//                try {
//                    QueryResult = context.getClassLoader().loadClass("com.taobao.wireless.security.adapter.JNICLibrary");
//                } catch (ClassNotFoundException e) {
//                    Log.e("xposed", "查询报错"+e.getMessage());
//                    e.printStackTrace();
//                }
//                Method[] m = QueryResult.getMethods();
//                for (final Method method : m) {
//                    //System.out.println(method.getName());
//                    Log.e("xposed ", method.getName()); //this is ok
//                }
//
//            }
//        });


//        XposedHelpers.findAndHookConstructor(clasz, new XC_MethodHook() {
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable{
//                XposedBridge.log("findAndHookConstructor method success...........");
//            }
//        });

            //  Log.e("kwing", "replaceHookedMethod112221: "+clazz11+STR);
    			/*----*/






//        XposedHelpers.findAndHookConstructor("dalvik.system.BaseDexClassLoader", loadPackageParam.classLoader, String.class, File.class, String.class, ClassLoader.class,
//                boolean.class, new XC_MethodHook() {
//
//
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            //Logger.printstack();
//                            Log.d("kwing", "afterHookedMethod: "+(String) param.args[0]);
//
//                        }
////                        for (int i = 0; i < param.args.length; i++) {
////                            XposedBridge.log(" argument is:" + param.args[i]);
////                        }
////
////                        int field_result = (int) XposedHelpers.getObjectField(param.thisObject,"pMoney");
////
////                        XposedBridge.log(String.valueOf(field_result));
//
//                });




			/*------------------------------------*/

  //     XposedHelpers.findAndHookMethod(clasz, "a",byte[].class,int.class, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    //向xposed installer中写入日志信息
////                    XposedBridge.log("changed method success...........");
//                    Log.e("kwing", "afterHookedMeth       od: "+param.args[0]+"  len="+param.args[1]);
//                    int str = (int)param.getResult();
//                    Log.e("kwing", "replaceHookedMethod: "+str);
//                }
//        }
//
//        );


//        XposedHelpers.findAndHookMethod(clazz11, "getView", new XC_MethodHook() {
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        //向xposed installer中写入日志信息
////                    XposedBridge.log("changed method success...........");
//                        Log.e("kwing", "afterHookedMethod: "+ param.getResult());
//
//                    }
//                }
//
//        );

//        hook_method("com.android.keyguard.KeyguardPatternView$UnlockPatternListener", loadPackageParam.classLoader, "onPatternDetected",List.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//
//
//                Log.i("kwing", "onPatternDetected args:");
//
//            }
//        });







   //    Log.e("hrst Loaded app: " ," loadPackageParam.packageName"+loadPackageParam.packageName);


//       hook_methods("android.location.LocationManager", "getLastKnownLocation", new XC_MethodHook(){
//           @Override
//           protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////               Log.e("jw", "hook getLastKnownLocation...");
//               Location l = new Location(LocationManager.PASSIVE_PROVIDER);
//               double lo = -10000d; //经度
//               double la = -10000d; //纬度
//               l.setLatitude(la);
//               l.setLongitude(lo);
//               param.setResult(l);
//           }
//       });

//       hook_methods("android.location.LocationManager", "requestLocationUpdates", new XC_MethodHook() {
//           @Override
//           protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
////               Log.i("jw", "hook requestLocationUpdates...");
//
//               if (param.args.length == 4 && (param.args[0] instanceof String)) {
//                   LocationListener ll = (LocationListener)param.args[3];
//                   Class<?> clazz = LocationListener.class;
//                   Method m = null;
//                   for (Method method : clazz.getDeclaredMethods()) {
//                       if (method.getName().equals("onLocationChanged")) {
//                           m = method;
//                           break;
//                       }
//                   }
//
//                   try {
//                       if (m != null) {
//                           Object[] args = new Object[1];
//                           Location l = new Location(LocationManager.PASSIVE_PROVIDER);
//                           double lo = -10000d; //经度
//                           double la = -10000d; //纬度
//                           l.setLatitude(la);
//                           l.setLongitude(lo);
//                           args[0] = l;
//                           m.invoke(ll, args);
//                       }
//                   } catch (Exception e) {
//                       XposedBridge.log(e);
//                   }
//               }
//           }
//       });









