package com.gzyslczx.ncfundscreenapp.adapters;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gzyslczx.ncfundscreenapp.R;
import com.gzyslczx.ncfundscreenapp.beans.response.ResMainRankInfo;

public class HomeRankRightAdapter extends BaseQuickAdapter<ResMainRankInfo, BaseViewHolder> {
    public HomeRankRightAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, ResMainRankInfo resMainRankInfo) {
        baseViewHolder.setText(R.id.DailyGainsValue, resMainRankInfo.getProfitRate());
        baseViewHolder.setText(R.id.WeekGainsValue, resMainRankInfo.getWeekRate());
        baseViewHolder.setText(R.id.MonthGainsValue, resMainRankInfo.getMonthRate());
        baseViewHolder.setText(R.id.QuarterGainsValue, resMainRankInfo.getMonthRate3());
        baseViewHolder.setText(R.id.HalfYearGainsValue, resMainRankInfo.getMonthRate6());
        baseViewHolder.setText(R.id.YearGainsValue, resMainRankInfo.getYearRate());
        baseViewHolder.setText(R.id.FundModeValue, resMainRankInfo.getNetValue());
    }
}
