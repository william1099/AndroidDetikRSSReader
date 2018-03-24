package william1099.com.design2;


import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class mySingleton {
    private static mySingleton mInstance = null;
    public RequestQueue mRequestQueue;
    public ImageLoader imageLoader;
    private mySingleton() {
        mRequestQueue = Volley.newRequestQueue(myApplication.getAppContext());
        imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            LruCache<String, Bitmap> mLruCache = new LruCache<>(50);
            @Override
            public Bitmap getBitmap(String url) {
                return mLruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mLruCache.put(url, bitmap);
            }
        });
    }
    public static mySingleton getInstance() {
        if (mInstance == null) {
            mInstance = new mySingleton();
        }
        return mInstance;
    }

}
