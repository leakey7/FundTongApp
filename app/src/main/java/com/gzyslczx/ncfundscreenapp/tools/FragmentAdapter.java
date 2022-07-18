package com.gzyslczx.ncfundscreenapp.tools;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.gzyslczx.ncfundscreenapp.fragments.BaseFragment;

import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {

    private List<BaseFragment> fragments;

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public FragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (fragments!=null){
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (fragments!=null){
            return fragments.size();
        }
        return 0;
    }
}
