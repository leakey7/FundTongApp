package com.gzyslczx.ncfundscreenapp.adapters;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gzyslczx.ncfundscreenapp.R;
import com.gzyslczx.ncfundscreenapp.beans.response.ResMainRankInfo;

public class HomeRankLeftAdapter extends BaseQuickAdapter<ResMainRankInfo, BaseViewHolder> {

    public HomeRankLeftAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ResMainRankInfo resMainRankInfo) {
        if (resMainRankInfo.getSortInfo()==2){
            baseViewHolder.setVisible(R.id.RankLeftImg, true);
            Glide.with(getContext()).load(ContextCompat.getDrawable(getContext(), R.drawable.no_1)).fitCenter()
                    .into((ImageView) baseViewHolder.getView(R.id.RankLeftImg));
        }else if (resMainRankInfo.getSortInfo()==3){
            baseViewHolder.setVisible(R.id.RankLeftImg, true);
            Glide.with(getContext()).load(ContextCompat.getDrawable(getContext(), R.drawable.no_2)).fitCenter()
                    .into((ImageView) baseViewHolder.getView(R.id.RankLeftImg));
        }else if (resMainRankInfo.getSortInfo()==4){
            baseViewHolder.setVisible(R.id.RankLeftImg, true);
            Glide.with(getContext()).load(ContextCompat.getDrawable(getContext(), R.drawable.no_3)).fitCenter()
                    .into((ImageView) baseViewHolder.getView(R.id.RankLeftImg));
        }else {
            baseViewHolder.setGone(R.id.RankLeftImg, true);
        }
        baseViewHolder.setText(R.id.RankLeftName, resMainRankInfo.getName());
        baseViewHolder.setText(R.id.RankLeftCode, resMainRankInfo.getFCode());
    }
}
