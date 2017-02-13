package com.example.ivandimitrov.restorantmenu.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivandimitrov.restorantmenu.MenuAdapter;
import com.example.ivandimitrov.restorantmenu.MenuItem;
import com.example.ivandimitrov.restorantmenu.R;

import java.util.ArrayList;

/**
 * Created by Ivan Dimitrov on 2/13/2017.
 */

public class FoodFragment extends Fragment {
    public static final int SOUP_PAGE      = 0;
    public static final int MAIN_DISH_PAGE = 1;
    public static final int DESSERT_PAGE   = 2;

    private ArrayList<MenuItem> mMenuItems = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MenuAdapter  mMenuAdapter;

    public static final Fragment newInstance(int message) {
        FoodFragment fragment = new FoodFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("pageType", message);
        fragment.setArguments(bdl);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.soup_fragment, container, false);
        int pageType = getArguments().getInt("pageType");
        switch (pageType) {
            case SOUP_PAGE:
                initMenuSoup();
                break;
            case MAIN_DISH_PAGE:
                initMenuMainDish();
                break;
            case DESSERT_PAGE:
                initMenuDesserts();
                break;
            default:
                break;
        }
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mMenuAdapter = new MenuAdapter(mMenuItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMenuAdapter);
        return v;
    }

    private void initMenuSoup() {
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup)
                , "CreamSoup", "Traditional soup from a special recipe"
                , "98", "16", "20", 3));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup2)
                , "CreamSoup 2", "Traditional soup from a not so special recipe"
                , "45", "12", "18", 0));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cream_soup3)
                , "CreamSoup 3", "Special soup from a awesome recipe"
                , "178", "15", "33", 5));
    }

    private void initMenuMainDish() {
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.spageti)
                , "Spageti", "Spaget Boloneze"
                , "98", "16", "20", 1));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.lazania)
                , "Lazania", "Lazania just likes Garfield like it"
                , "45", "12", "18", 2));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.rice)
                , "Rice", "Fresh rice gathered from China"
                , "178", "15", "33", 5));
    }

    private void initMenuDesserts() {
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.icecream)
                , "IceCream", "As cold as ice"
                , "98", "16", "20", 3));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.brownies)
                , "Brownies 2", "They are not the \"Special\" brownies"
                , "45", "12", "18", 4));
        mMenuItems.add(new MenuItem(BitmapFactory.decodeResource(getResources(), R.drawable.cake)
                , "Cake", "Its like diebiteies in a plate"
                , "178", "15", "33", 5));
    }
}