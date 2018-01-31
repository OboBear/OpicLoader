package com.image.loader.library.engine;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author obo
 * @date 2018/1/30
 */

public class MemoryEngine extends BaseEngine {
    private static final int DEFAULT_SIZE = 18;
    private volatile LruCache<String, Bitmap> mBitmapLruCache;

    @Override
    public void load(String url, final EngineListener listener) {
        if (mBitmapLruCache == null) {
            mBitmapLruCache = new LruCache<>(DEFAULT_SIZE);
        }

        Bitmap result = mBitmapLruCache.get(url);
        if (result != null) {
            listener.onLoad(url, result);
        } else {
            if (next != null) {
                next.load(url, new EngineListener() {
                    @Override
                    public void onLoad(String url, Bitmap bitmap) {
                        mBitmapLruCache.put(url, bitmap);
                        listener.onLoad(url, bitmap);
                    }
                    @Override
                    public void onError(String url) {
                        listener.onError(url);
                    }
                });
            } else {
                listener.onError(url);
            }
        }

    }

    @Override
    public void update(String url, Bitmap bitmap) {
        mBitmapLruCache.put(url, bitmap);
    }
}
