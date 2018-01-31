package com.image.loader.library.engine;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author obo
 * @date 2018/1/30
 */

public class EngineManager extends BaseEngine {

    private static EngineManager INSTANCE;

    private EngineManager() {
        init();
    }

    public static EngineManager getInstance() {
        if (INSTANCE == null) {
            synchronized (EngineManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EngineManager();
                }
            }
        }
        return INSTANCE;
    }

    private void init() {
        List<BaseEngine> engines = new ArrayList<>();
        engines.add(new MemoryEngine());
        engines.add(new DiskEngine());
        engines.add(new NetworkEngine());
        next = makeChain(engines);
    }

    private BaseEngine makeChain(List<BaseEngine> engines) {
        int size = engines.size();
        for (int i = 0; i < size - 1; i++) {
            engines.get(i).next = engines.get(i + 1);
        }
        return engines.get(0);
    }

    @Override
    public void load(String url, final EngineListener listener) {
        if (next != null) {
            next.load(url, new EngineListener() {
                @Override
                public void onLoad(String url, Bitmap bitmap) {
                    listener.onLoad(url, bitmap);
                }

                @Override
                public void onError(String url) {
                    listener.onError(url);
                }
            });
        }
    }

    @Override
    public void update(String url, Bitmap bitmap) {

    }
}
