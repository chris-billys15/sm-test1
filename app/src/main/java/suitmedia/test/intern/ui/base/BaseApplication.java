package suitmedia.test.intern.ui.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    private static Context context;

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
