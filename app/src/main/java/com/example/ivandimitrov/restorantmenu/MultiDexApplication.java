package com.example.ivandimitrov.restorantmenu;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Ivan Dimitrov on 2/14/2017.
 */

public class MultiDexApplication extends Application {
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

}
