package com.image.loader.library.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.image.loader.library.disklrucache.DiskLruCache;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author obo
 * @date 2018/1/30
 */

public class DiskEngine extends BaseEngine {
    private static final long DEFAULT_CACHE_SIZE = 1024L * 1024 * 50;
    private static final int DEFAULT_APP_VERRSION = 1;
    private static final int DEFAULT_COUNT = 1;
    private DiskLruCache mDiskLruCache;

    public DiskEngine() {
        File diskCacheDir = new File(Environment.getExternalStorageDirectory() + "/bitmap");
        if(!diskCacheDir.exists()){
            diskCacheDir.mkdirs();
        }
        try {
            mDiskLruCache = DiskLruCache.open(diskCacheDir,
                    DEFAULT_APP_VERRSION,
                    DEFAULT_COUNT,
                    DEFAULT_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(String url, final EngineListener listener) {
        if (mDiskLruCache != null) {
            DiskLruCache.Snapshot snapshot = null;
            try {
                String key = EngineUtil.Md5(url);
                snapshot = mDiskLruCache.get(key);
                if (snapshot != null) {
                    FileInputStream fileInputStream = (FileInputStream) snapshot.getInputStream(0);
                    FileDescriptor fileDescriptor = fileInputStream.getFD();
                    Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                    listener.onLoad(url, bitmap);
                } else {
                    loadNext(url, listener);
                }
            } catch (IOException e) {
                loadNext(url, listener);
            }
        }
    }

    private void loadNext(String url, final EngineListener listener) {
        if (next != null) {
            next.load(url, new EngineListener() {
                @Override
                public void onLoad(String url, Bitmap bitmap) {
                    listener.onLoad(url, bitmap);
                    diskCache(url, bitmap);
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

    private void diskCache(String url, Bitmap bitmap) {
        String key = EngineUtil.Md5(url);
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                editor.commit();
                mDiskLruCache.flush();//刷新
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String url, Bitmap bitmap) {

    }
}
