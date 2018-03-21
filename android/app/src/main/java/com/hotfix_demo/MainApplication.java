package com.hotfix_demo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSCJavaScriptExecutorFactory;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ProxyJavaScriptExecutor;
import com.facebook.react.devsupport.WebsocketJavaScriptExecutor;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        try {
            if (true) {
                Class<?> RIManagerClazz = mReactNativeHost.getReactInstanceManager().getClass();
                Method method = RIManagerClazz.getDeclaredMethod("recreateReactContextInBackground",
                        JavaScriptExecutorFactory.class, JSBundleLoader.class);
                method.setAccessible(true);
                method.invoke(mReactNativeHost.getReactInstanceManager(),
                        new JSCJavaScriptExecutorFactory(getPackageName(), AndroidInfoHelpers.getFriendlyDeviceName()),
                        JSBundleLoader.createFileLoader(Environment.getExternalStorageDirectory().toString() + File.separator + "index.android.bundle"));
            } else {
                Class<?> RIManagerClazz = mReactNativeHost.getReactInstanceManager().getClass();
                Method[] method = RIManagerClazz.getDeclaredMethods();
                for (Method m : method) {
                    Log.d("MainApplication", m.toString());
                }

            }

            /**
             *  ReactInstanceManager.createReactContextInBackground()
             03-21 16:02:14.699 30642-30642/? D/ReactNative: ReactInstanceManager.recreateReactContextInBackgroundInner()
             03-21 16:02:14.699 30642-30642/? D/ReactNative: ReactInstanceManager.recreateReactContextInBackgroundFromBundleLoader()
             03-21 16:02:14.699 30642-30642/? D/ReactNative: ReactInstanceManager.recreateReactContextInBackground()
             03-21 16:02:14.699 30642-30642/? D/ReactNative: ReactInstanceManager.runCreateReactContextOnNewThread()
             */

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
