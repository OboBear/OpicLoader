package com.image.loader.library;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.image.loader.library.engine.BaseEngine;
import com.image.loader.library.engine.EngineManager;

/**
 * @author obo
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    ImageView ivTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivTest = findViewById(R.id.iv_test);
    }

    private void load() {
        OpicLoader.with(this)
                .load("https://ss1.bdstatic.com/5aAHeD3nKgcUp2HgoI7O1ygwehsv/media/ch1000/png/yuequanshibiaoti.png")
                .into(ivTest);
//        EngineManager engineManager = EngineManager.getInstance();
//        engineManager.load("https://ss1.bdstatic.com/5aAHeD3nKgcUp2HgoI7O1ygwehsv/media/ch1000/png/yuequanshibiaoti.png", new BaseEngine.EngineListener() {
//            @Override
//            public void onLoad(String url, final Bitmap bitmap) {
//                Log.i(TAG, "");
//                new Handler(Looper.getMainLooper()).post(new Runnable() {
//                    @Override
//                    public void run() {
//                        ivTest.setImageBitmap(bitmap);
//                    }
//                });
//            }
//
//            @Override
//            public void onError(String url) {
//                Log.i(TAG, "");
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        load();
    }
}
