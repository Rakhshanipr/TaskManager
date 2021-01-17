package com.example.taskmanager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.taskmanager.view.fragment.ListTaskFragment;

public class TaskViewPagerAdapter extends FragmentStateAdapter {

    public TaskViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ListTaskFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
