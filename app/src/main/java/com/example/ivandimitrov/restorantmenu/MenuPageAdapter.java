package com.example.ivandimitrov.restorantmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Ivan Dimitrov on 2/13/2017.
 */

public class MenuPageAdapter extends FragmentPagerAdapter {
    public static final String PAGE_SOUPS      = "Soups";
    public static final String PAGE_MAIN       = "Main dish";
    public static final String PAGE_DESSERTS   = "Desserts";
    public static final String PAGE_RESTAURANT = "Restaurant";
    public static final String PAGE_PAGE_ERROR = "PAGE ERROR";

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
            case MainActivity.SOUP_PAGE:
                return PAGE_SOUPS;
            case MainActivity.MAIN_DISH_PAGE:
                return PAGE_MAIN;
            case MainActivity.DESSERT_PAGE:
                return PAGE_DESSERTS;
            case MainActivity.RESTAURANT_PAGE:
                return PAGE_RESTAURANT;
            default:
                return PAGE_PAGE_ERROR;
        }
    }
}
