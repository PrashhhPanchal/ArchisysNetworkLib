package com.archisys.archisysnetworklib;

import android.app.Application;
import android.content.Context;

import com.archisys.archisys_network_lib.RestApiManagerModel;

public class LibBaseApplication extends Application {
    static Context _CurrentContext;


    public static Context getCurrentContext(){
        return _CurrentContext;
    }

    public LibBaseApplication(){
        _CurrentContext = this;
    }

}
