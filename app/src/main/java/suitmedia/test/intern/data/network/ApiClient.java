package suitmedia.test.intern.data.network;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import suitmedia.test.intern.ui.base.BaseApplication;
import suitmedia.test.intern.util.Utility;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
        OkHttpClient client;
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        if (retrofit == null) {
            Cache cache = new Cache(BaseApplication.getAppContext().getCacheDir(), cacheSize);
            client = new OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(chain -> {
                        Request request = chain.request();
                        if(!isNetworkAvailable()){
                            int maxStale = 60 * 60 * 24 * 7;
                            request = request
                                    .newBuilder()
                                    .header("Cache-Control","public, only-if-cached, max-stale=" + maxStale)
                                    .build();
                        }
                        else{
                            request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build();
                        }
                        return chain.proceed(request);
                    })
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Utility.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    private static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) BaseApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}