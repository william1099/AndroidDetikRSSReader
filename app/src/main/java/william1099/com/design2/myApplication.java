package william1099.com.design2;

import android.app.Application;
import android.content.Context;


public class myApplication extends Application {
    private static myApplication myapp;
    Context ctx;
    @Override
    public void onCreate() {
        super.onCreate();
        myapp = this;
    }

    public static Context getAppContext() {
        return myapp.getApplicationContext();
    }
}
