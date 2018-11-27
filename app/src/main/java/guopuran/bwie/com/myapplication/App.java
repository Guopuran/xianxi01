package guopuran.bwie.com.myapplication;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSizePercentage(10)
                .diskCacheSize(20*1024*1024)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
