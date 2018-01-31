package com.image.loader.library.engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author obo
 * @date 2018/1/30
 */

public class NetworkEngine extends BaseEngine {

    @Override
    public void load(final String url, final EngineListener listener) {
        FutureTask<Bitmap> futureTask = new FutureTask<Bitmap>(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {

                URL imgUrl = null;
                try {
                    imgUrl = new URL(url);
                    InputStream inputStream = imgUrl.openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    if (bitmap != null) {
                        listener.onLoad(url, bitmap);
                    } else {
                        listener.onError(url);
                    }
                    return bitmap;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        EngineUtil.executor(futureTask);

    }

    @Override
    public void update(String url, Bitmap bitmap) {

    }
}
