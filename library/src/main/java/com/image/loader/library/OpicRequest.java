package com.image.loader.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import android.widget.ImageView;

import com.image.loader.library.engine.BaseEngine;
import com.image.loader.library.engine.EngineManager;

import java.lang.ref.WeakReference;

/**
 * @author obo
 * @date 2018/1/30
 */

public class OpicRequest {
    private WeakReference<Context> mContextWeakReference;
    private String mRequestUrl;
    private LruCache<String, Bitmap> mMemoryCache;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    public OpicRequest(Context context) {
        mContextWeakReference = new WeakReference<>(context);
    }
    public OpicRequest load(String url) {
        mRequestUrl = url;
        return this;
    }

    public void into(final ImageView imageView) {
        EngineManager engineManager = EngineManager.getInstance();
        engineManager.load(mRequestUrl, new BaseEngine.EngineListener() {
            @Override
            public void onLoad(String url, final Bitmap bitmap) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void onError(String url) {

            }
        });
    }
}
