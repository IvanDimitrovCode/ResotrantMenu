package com.example.ivandimitrov.restorantmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Ivan Dimitrov on 2/13/2017.
 */

public class MenuPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public MenuPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Soups";
            case 1:
                return "Main dish";
            case 2:
                return "Desserts";
            default:
                return "PAGE ERROR";
        }
    }
}
