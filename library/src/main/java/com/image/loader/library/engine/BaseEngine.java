package com.image.loader.library.engine;

import android.graphics.Bitmap;

/**
 * @author obo
 * @date 2018/1/30
 */

public abstract class BaseEngine {
    protected BaseEngine next;
    public interface EngineListener {
        void onLoad(String url, Bitmap bitmap);
        void onError(String url);
    }
    public BaseEngine setNext(BaseEngine next) {
        this.next = next;
        return this;
    }
    public abstract void load(String url, EngineListener listener);
    public abstract void update(String url, Bitmap bitmap);
}
