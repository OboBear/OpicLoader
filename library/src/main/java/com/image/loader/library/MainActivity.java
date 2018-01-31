package com.image.loader.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * @author obo
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    ImageView ivTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ivTest = findViewById(R.id.iv_test);
    }

    private void load() {
        OpicLoader.with(this)
                .load("https://ss1.bdstatic.com/5aAHeD3nKgcUp2HgoI7O1ygwehsv/media/ch1000/png/yuequanshibiaoti.png")
                .into(ivTest);
    }

    @Override
    public void onClick(View v) {
        load();
    }
}
