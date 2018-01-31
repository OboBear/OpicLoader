package com.image.loader.imageloader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.image.loader.imageloader.databinding.ItemImgBinding;
import com.image.loader.library.OpicLoader;

import java.util.List;

/**
 * @author obo
 * @date 2018/1/31
 */

public class CustomAdapter extends RecyclerView.Adapter {
    private List<String> mImagePath;
    private LayoutInflater mLayoutInflater;
    public CustomAdapter(Context context, List<String> imagePath) {
        mImagePath = imagePath;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomViewHolder(ItemImgBinding.inflate(mLayoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CustomViewHolder customViewHolder = (CustomViewHolder) holder;
        customViewHolder.setPath(mImagePath.get(position));
    }

    @Override
    public int getItemCount() {
        return mImagePath.size();
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        private ItemImgBinding mItemImgBinding;
        public CustomViewHolder(ItemImgBinding itemImgBinding) {
            super(itemImgBinding.getRoot());
            mItemImgBinding = itemImgBinding;
        }

        private void setPath(String path) {
            OpicLoader.with(null).load(path).into(mItemImgBinding.ivTest);
        }
    }


}
