package com.image.loader.library.engine;

import android.support.annotation.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author obo
 * @date 2018/1/31
 */

public class EngineUtil {
    private  static ExecutorService mExecutorService;

    public static void executor(FutureTask futureTask) {
        if (mExecutorService == null) {
            synchronized (EngineUtil.class) {
                if (mExecutorService == null) {
                    ThreadFactory factory = new ThreadFactory() {
                        @Override
                        public Thread newThread(@NonNull Runnable r) {
                            return new Thread(r, "name");
                        }
                    };

                    mExecutorService = new ThreadPoolExecutor(10, 20, 0L,  TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), factory);
                }
            }
        }
        mExecutorService.submit(futureTask);
    }

    /**
     * 使用MD5算法对传入的key进行加密并返回。
     */
    public static String Md5(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
