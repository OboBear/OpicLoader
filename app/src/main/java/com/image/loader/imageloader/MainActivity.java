package com.image.loader.imageloader;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.image.loader.imageloader.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author obo
 */
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mActivityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mActivityMainBinding.rvContent.setLayoutManager(new LinearLayoutManager(this));
        mActivityMainBinding.rvContent.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        mActivityMainBinding.rvContent.setAdapter(new CustomAdapter(this, generalPathList()));
    }
    private List<String> generalPathList() {
        List<String> pathList = new ArrayList<>();
        pathList.add("https://ss1.bdstatic.com/5aAHeD3nKgcUp2HgoI7O1ygwehsv/media/ch1000/png/yuequanshibiaoti.png");
        pathList.add("http://img4.imgtn.bdimg.com/it/u=3357021395,3491635869&fm=27&gp=0.jpg");
        pathList.add("http://img5.imgtn.bdimg.com/it/u=49366202,632101467&fm=27&gp=0.jpg");
        pathList.add("http://img5.imgtn.bdimg.com/it/u=161888459,1712714238&fm=27&gp=0.jpg");
        pathList.add("http://img1.imgtn.bdimg.com/it/u=594559231,2167829292&fm=27&gp=0.jpg");
        pathList.add("http://img1.imgtn.bdimg.com/it/u=594559231,2167829292&fm=27&gp=0.jpg");
        pathList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1665207864,746409922&fm=27&gp=0.jpg");
        pathList.add("https://ss1.bdstatic.com/5aAHeD3nKgcUp2HgoI7O1ygwehsv/media/ch1000/png/yuequanshibiaoti.png");
        pathList.add("https://ss1.bdstatic.com/5aAHeD3nKgcUp2HgoI7O1ygwehsv/media/ch1000/png/yuequanshibiaoti.png");
        pathList.add("https://ss1.bdstatic.com/5aAHeD3nKgcUp2HgoI7O1ygwehsv/media/ch1000/png/yuequanshibiaoti.png");
        pathList.add("https://ss1.bdstatic.com/5aAHeD3nKgcUp2HgoI7O1ygwehsv/media/ch1000/png/yuequanshibiaoti.png");
        return pathList;
    }
}
